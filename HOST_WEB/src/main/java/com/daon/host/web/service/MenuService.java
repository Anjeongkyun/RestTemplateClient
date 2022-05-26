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
import com.daon.host.web.mapper.MenuMapper;
import com.daon.host.web.mapper.NoticeMapper;
import com.daon.host.web.mapper.ParkMapper;
import com.daon.host.web.vo.LoginVo;
import com.daon.host.web.vo.MenuVo;
import com.daon.host.web.vo.Notice;
import com.daon.host.web.vo.ParkVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenuService {

	@Autowired
    private Validator validator;
	
	private final Response response;
	
	@Autowired
	private MenuMapper menuMapper;
	

	public ResponseEntity<?> getList(@Valid MenuVo.CRUD menu) {
		if (ObjectUtils.isEmpty(menu)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		List<MenuVo.CRUD> list = menuMapper.getList(menu);

		if (ObjectUtils.isEmpty(list)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(list, "조회 성공했습니다.", HttpStatus.OK);
	}


	public ResponseEntity<?> setMenu(@Valid MenuVo.CRUD user) {
		if (ObjectUtils.isEmpty(user)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		int userVoTable;
	
		if (user.getRegType().equals("add") ) {
			userVoTable = menuMapper.insertMenu(user);
		} else if (user.getRegType().equals("change") ) {
			userVoTable = menuMapper.updateMenu(user);
		} else if (user.getRegType().equals("delete") ) {
			userVoTable = menuMapper.deleteMenu(user);
		} else {
			return response.failResult("CURD가 올바르지 않습니다.", HttpStatus.BAD_REQUEST);
		}

		if (ObjectUtils.isEmpty(userVoTable)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(userVoTable, "조회 성공했습니다.", HttpStatus.OK);
	}


}
