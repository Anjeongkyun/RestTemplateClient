package com.daon.host.web.service;

import java.util.List;

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
public class LoginService_old {

	private final Response response;
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private LoginMapper loginMapper;
	
	public ResponseEntity<?> noticeNo1(LoginVo loginVo) {
    	if(ObjectUtils.isEmpty(loginVo) ) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
    	
    	
		return response.success(loginVo, "조회 성공했습니다.", HttpStatus.OK);
	}

	public ResponseEntity<?> selectNo1(String noticeNo) {
    	if(ObjectUtils.isEmpty(noticeNo) ) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
    	Notice notice = noticeMapper.selectNo(noticeNo);
    	
    	if(ObjectUtils.isEmpty(notice)) {
    		return response.fail("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(notice, "조회 성공했습니다.", HttpStatus.OK);
	}
	public ResponseEntity<?> selectNo(LoginVo loginVo1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<?> selectNo3() {
		// TODO Auto-generated method stub
		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
	}


	public ResponseEntity<?> putLogin1(LoginVo loginVo) {
		
		System.out.println(loginVo);
		if(ObjectUtils.isEmpty(loginVo) ) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
    	LoginVo logVo = loginMapper.getLogin(loginVo);
    	
    	if(ObjectUtils.isEmpty(logVo)) {
    		return response.fail("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(logVo, "조회 성공했습니다.", HttpStatus.OK);
	}

	public ResponseEntity<?> putLoginAll(LoginVo loginVo) {
		// TODO Auto-generated method stub
		System.out.println(loginVo);
		if(ObjectUtils.isEmpty(loginVo) ) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
    	List<LoginVo> logVo = loginMapper.getLoginAll(loginVo);
    	
    	if(ObjectUtils.isEmpty(logVo)) {
    		return response.fail("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(logVo, "조회 성공했습니다.", HttpStatus.OK);
	}


	
}
