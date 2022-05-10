package com.daon.host.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.mapper.LoginMapper;
import com.daon.host.web.service.LoginService;
import com.daon.host.web.vo.LoginVo;

import lombok.AllArgsConstructor;


@RestController // rest api 를 사용하기 위함
@AllArgsConstructor
@RequestMapping("/internal/davis/web/user")

public class UsersController {
	
	/* jpa flow
	 * Controller -> Service -> Repository(JpaRepository 상속 인터페이스)
	 * mybatis flow
	 * Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML)
	 참고: https://blog.jiniworld.me/55
	 * */
	@Autowired
	private LoginMapper loginMapper; 
	
	private final LoginService loginService;
	
	
	
		
}