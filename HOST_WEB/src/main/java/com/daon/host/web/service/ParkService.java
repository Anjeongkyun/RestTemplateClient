package com.daon.host.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.ParkMapper;
import com.daon.host.web.vo.Notice;
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

	public ResponseEntity<?> getDetail(String aptId) {
		if(ObjectUtils.isEmpty(aptId)) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
    	ParkVo parkVo = parkMapper.getDetail(aptId);
    	
    	if(ObjectUtils.isEmpty(parkVo)) {
    		return response.fail("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(parkVo, "조회 성공했습니다.", HttpStatus.OK);
	}

	public ResponseEntity<?> insert(ParkVo parkVo) {
    	if(ObjectUtils.isEmpty(parkVo) ) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
    	parkMapper.insert(parkVo);
    	
		return response.success("단지 정보 생성 OK");
	}
	
	public ResponseEntity<?> update(ParkVo parkVo) {
    	if(ObjectUtils.isEmpty(parkVo)) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
    	parkMapper.update(parkVo);
    	
		return response.success("단지 정보 수정 OK");
	}

	public ResponseEntity<?> delete(String aptId) {
    	if(ObjectUtils.isEmpty(aptId)) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
    	parkMapper.delete(aptId);
    	
		return response.success("단지 정보 삭제 OK");
	}


	

}
