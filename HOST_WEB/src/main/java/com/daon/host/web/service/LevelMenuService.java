package com.daon.host.web.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.LevelMenuMapper;
import com.daon.host.web.vo.LevelMenuVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class LevelMenuService {

	@Autowired
    private Validator validator;
	
	private final Response response;
	
	@Autowired
	private LevelMenuMapper levelMenuMapper;

	public ResponseEntity<?> getList(@Valid LevelMenuVo.CRUD levelMenu) {
		if (ObjectUtils.isEmpty(levelMenu)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		List<LevelMenuVo.CRUD> list = levelMenuMapper.getList(levelMenu);

		if (ObjectUtils.isEmpty(list)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(list, "조회 성공했습니다.", HttpStatus.OK);
	}

	public ResponseEntity<?> setLevel(@Valid LevelMenuVo.CRUD levelMenu) {
		if (ObjectUtils.isEmpty(levelMenu)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		int result;
	
		if (levelMenu.getRegType().equals("add") ) {
			result = levelMenuMapper.insertLevel(levelMenu);
		} else if (levelMenu.getRegType().equals("change") ) {
			result = levelMenuMapper.updateLevel(levelMenu);
		} else if (levelMenu.getRegType().equals("delete") ) {
			result = levelMenuMapper.deleteLevel(levelMenu);
		} else {
			return response.failResult("CURD가 올바르지 않습니다.", HttpStatus.BAD_REQUEST);
		}

		if (ObjectUtils.isEmpty(result)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(result, "조회 성공했습니다.", HttpStatus.OK);
	}


}
