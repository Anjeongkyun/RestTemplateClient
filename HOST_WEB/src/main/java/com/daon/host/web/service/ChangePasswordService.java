package com.daon.host.web.service;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.ChangePasswordMapper;
import com.daon.host.web.vo.ChangePasswordVo;

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

	public ResponseEntity<?> setChangePassword(  ChangePasswordVo.ChangePassword changePassword) {
		// TODO Auto-generated method stub
		
		
		if (ObjectUtils.isEmpty(changePassword)) {
			return response.fail("파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}
		
		//사용자 비밀번호 확인
		String changePasswordVo1 = changePasswordMapper.pwCheck(changePassword);
		
		if (ObjectUtils.isEmpty(changePasswordVo1)) {
			return response.fail("현재 비밀번호 확인이 필요합니다.", HttpStatus.BAD_REQUEST);
		}

		//확인되면 변경
		int changePasswordVo2 = changePasswordMapper.setChangePassword(changePassword);

		if (ObjectUtils.isEmpty(changePasswordVo2)) {
			return response.fail("변경 실패했습니다.", HttpStatus.BAD_REQUEST);
		}

		return response.successResult(changePasswordVo2, "변경 성공했습니다.", HttpStatus.OK);
	}

}
