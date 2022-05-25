package com.daon.host.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.ParkMapper;
import com.daon.host.web.vo.ParkVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ParkService {

	@Autowired
	private ParkMapper parkMapper;	
	private final Response response;	
	
	//단지 리스트 조회 
	public ResponseEntity<?> getList() { 
		
    	List<ParkVo> parkList = parkMapper.getList();
    	
    	if(ObjectUtils.isEmpty(parkList)) {
    		return response.fail("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(parkList, "조회 성공했습니다.", HttpStatus.OK);
	}

}
