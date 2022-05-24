package com.daon.host.web.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.LoginMapper;
import com.daon.host.web.mapper.NoticeMapper;
import com.daon.host.web.mapper.ParkMapper;
import com.daon.host.web.vo.LoginVo;
import com.daon.host.web.vo.Notice;
import com.daon.host.web.vo.ParkVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ParkService {

	@Autowired
    private Validator validator;
	
	private final Response response;
	
	@Autowired
	private ParkMapper parkMapper;


	public ResponseEntity<?> getPark(@Valid ParkVo.Park park) {
		if (ObjectUtils.isEmpty(park)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		List<ParkVo.Table> parkVoTable = parkMapper.getPark(park);

		if (ObjectUtils.isEmpty(parkVoTable)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(parkVoTable, "조회 성공했습니다.", HttpStatus.OK);
	}

}
