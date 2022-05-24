package com.daon.host.web.service;

import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.CmmnMapper;
import com.daon.host.web.vo.CmmnVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CmmnService {

	@Autowired
    private Validator validator;
	
	private final Response response;

	@Autowired
	private CmmnMapper cmmnMapper;

	
	public ResponseEntity<?> getCode(CmmnVo.CmmnCode cmmnCode) {
		// TODO Auto-generated method stub
		if (ObjectUtils.isEmpty(cmmnCode)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		List<CmmnVo.CmmnCode> cmmnCode2 = cmmnMapper.getCmmnCode(cmmnCode);

		if (ObjectUtils.isEmpty(cmmnCode2)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(cmmnCode2, "조회 성공했습니다.", HttpStatus.OK);
	}


	public ResponseEntity<?> getBase(CmmnVo.CmmnBase cmmnBase) {
		// TODO Auto-generated method stub
				if (ObjectUtils.isEmpty(cmmnBase)) {
					return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
				}

				List<CmmnVo.CmmnBase> cmmnBase2 = cmmnMapper.getCmmnBase(cmmnBase);

				if (ObjectUtils.isEmpty(cmmnBase2)) {
					return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
				}

				return response.successList(cmmnBase2, "조회 성공했습니다.", HttpStatus.OK);
	}

}
