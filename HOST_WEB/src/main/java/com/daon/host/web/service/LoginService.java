package com.daon.host.web.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.LoginMapper;
import com.daon.host.web.mapper.NoticeMapper;
import com.daon.host.web.vo.LoginVo;
import com.daon.host.web.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

	@Autowired
    private Validator validator;
	
	private final Response response;

	@Autowired
	private LoginMapper loginMapper;

	public ResponseEntity<?> getLogin( LoginVo.Login login) {
		// TODO Auto-generated method stub


		if (ObjectUtils.isEmpty(login)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		LoginVo.LoginResult loginVo2 = loginMapper.getLogin(login);

		if (ObjectUtils.isEmpty(loginVo2)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successLogin(loginVo2, "조회 성공했습니다.", HttpStatus.OK);
	}

}
