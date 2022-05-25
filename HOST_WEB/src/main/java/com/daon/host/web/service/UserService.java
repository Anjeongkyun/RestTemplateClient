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
import com.daon.host.web.mapper.ParkMapper;
import com.daon.host.web.mapper.UserMapper;
import com.daon.host.web.vo.ParkVo;
import com.daon.host.web.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import utils.Helper;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
	private Validator validator;

	private final Response response;

	@Autowired
	private UserMapper userMapper;

	public ResponseEntity<?> getUser(@Valid UserVo.User user) {
		if (ObjectUtils.isEmpty(user)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		List<UserVo.Table> userVoTable = userMapper.getUser(user);

		if (ObjectUtils.isEmpty(userVoTable)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(userVoTable, "조회 성공했습니다.", HttpStatus.OK);
	}

	public ResponseEntity<?> userManage(@Valid UserVo.CRUD user) {

		if (ObjectUtils.isEmpty(user)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		int userVoTable;
	
		if (user.getRegType().equals("add") ) {
			userVoTable = userMapper.insertUserManage(user);
		} else if (user.getRegType().equals("change") ) {
			userVoTable = userMapper.updateUserManage(user);
		} else if (user.getRegType().equals("delete") ) {
			userVoTable = userMapper.deleteUserManage(user);
		} else {
			return response.failResult("CURD가 올바르지 않습니다.", HttpStatus.BAD_REQUEST);
		}

		if (ObjectUtils.isEmpty(userVoTable)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(userVoTable, "조회 성공했습니다.", HttpStatus.OK);

	}

	public ResponseEntity<?> insertUserManage(@Valid UserVo.Table user) {
		if (ObjectUtils.isEmpty(user)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		int userVoTable = userMapper.insertUserManage(user);

		if (ObjectUtils.isEmpty(userVoTable)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(userVoTable, "조회 성공했습니다.", HttpStatus.OK);
	}

	public ResponseEntity<?> updateUserManage(@Valid UserVo.Table user) {
		if (ObjectUtils.isEmpty(user)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		int userVoTable = userMapper.updateUserManage(user);

		if (ObjectUtils.isEmpty(userVoTable)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(userVoTable, "조회 성공했습니다.", HttpStatus.OK);
	}

	public ResponseEntity<?> deleteUserManage(@Valid UserVo.Table user) {
		if (ObjectUtils.isEmpty(user)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		int userVoTable = userMapper.deleteUserManage(user);

		if (ObjectUtils.isEmpty(userVoTable)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(userVoTable, "조회 성공했습니다.", HttpStatus.OK);
	}

	public ResponseEntity<?> getIdCheck(@Valid UserVo.CRUD user) {
		if (ObjectUtils.isEmpty(user)) {
			return response.failResult("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
		}

		List<UserVo.Table>userVoTable = userMapper.getIdCheck(user);

		if (ObjectUtils.isEmpty(userVoTable)) {
			return response.failResult("조회된 정보가 없습니다. 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		return response.successList(userVoTable, "조회 성공했습니다.", HttpStatus.OK);
	}

}