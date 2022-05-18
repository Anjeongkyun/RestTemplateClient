package com.daon.host.web.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
public class MenuService {

	@Autowired
    private Validator validator;
	
	private final Response response;


}
