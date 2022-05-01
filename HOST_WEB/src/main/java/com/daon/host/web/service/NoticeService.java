package com.daon.host.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.NoticeMapper;
import com.daon.host.web.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeService {

	private final Response response;
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	public ResponseEntity<?> selectNo(String noticeNo) {
    	if(ObjectUtils.isEmpty(noticeNo) ) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
    	Notice notice = noticeMapper.selectNo(noticeNo);
    	
    	if(ObjectUtils.isEmpty(notice)) {
    		return response.fail("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(notice, "조회 성공했습니다.", HttpStatus.OK);
	}
	
}
