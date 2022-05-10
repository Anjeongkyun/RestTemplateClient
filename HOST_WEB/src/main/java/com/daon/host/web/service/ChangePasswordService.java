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
import com.daon.host.web.mapper.ChangePasswordMapper;
import com.daon.host.web.mapper.LoginMapper;
import com.daon.host.web.mapper.NoticeMapper;
import com.daon.host.web.vo.ChangePasswordVo;
import com.daon.host.web.vo.LoginVo;
import com.daon.host.web.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChangePasswordService {

	@Autowired
    private Validator validator;
	
	private final Response response;

	@Autowired
	private ChangePasswordMapper changePasswordMapper;

	public ResponseEntity<?> setPassword(  ChangePasswordVo.ChangePassword changePassword) {
		// TODO Auto-generated method stub


		if (ObjectUtils.isEmpty(changePassword)) {
			return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		ChangePasswordVo.Table changePasswordVo2 = changePasswordMapper.setChangePassword(changePassword);

		if (ObjectUtils.isEmpty(changePasswordVo2)) {
			return response.fail("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successLogin(changePasswordVo2, "조회 성공했습니다.", HttpStatus.OK);
	}

}
