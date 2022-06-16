package com.daon.host.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.web.dto.Response;
import com.daon.host.web.entity.NoticeEntity;
import com.daon.host.web.entity.ParkEntity;
import com.daon.host.web.mapper.ParkMapper;
import com.daon.host.web.repository.ParkRepository;
import com.daon.host.web.vo.NoticeVo;
import com.daon.host.web.vo.ParkVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ParkService {

	
	private final ParkMapper parkMapper;	
	private final Response response;	
	private final ParkRepository parkRepository;
	
	//단지 리스트 조회 
	public ResponseEntity<?> getList() { 
		
    	List<ParkEntity> parkList = parkRepository.
    			findAll(Sort.by(Sort.Direction.DESC, "insDate"));
    	
    	if(ObjectUtils.isEmpty(parkList)) {
    		return response.fail("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(parkList, "조회 성공했습니다.", HttpStatus.OK);
	}

	public ResponseEntity<?> getDetail(String aptId) {
		if(ObjectUtils.isEmpty(aptId)) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
		
		Optional<ParkEntity> park = parkRepository.findByAptId(aptId);
    	
        if(!park.isPresent()){
        	return response.fail("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
    	
		return response.success(park, "조회 성공했습니다.", HttpStatus.OK);
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
		
    	parkRepository.deleteByAptId(aptId);    	
    	
		return response.success("단지 정보 삭제 OK");
	}


	

}
