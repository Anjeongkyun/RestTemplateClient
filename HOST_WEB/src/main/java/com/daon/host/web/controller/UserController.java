package com.daon.host.web.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.dto.Response;
import com.daon.host.web.service.UserService;
import com.daon.host.web.vo.UserVo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import utils.Helper;

@Slf4j
@RestController // rest api 를 사용하기 위함
//@CrossOrigin(origins = "*", allowedHeaders = "*")

@Data
@RequestMapping("/internal/davis/web/user")
public class UserController {

	/*
	 * jpa flow Controller -> Service -> Repository(JpaRepository 상속 인터페이스) mybatis
	 * flow Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML) 참고:
	 * https://blog.jiniworld.me/55
	 */

	private final UserService userService;
	private final Response response;
	
	@PostMapping("/list")
	public ResponseEntity<?>  getUser(@Valid @RequestBody UserVo.User user, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return userService.getUser(user);
	}

	
	@PostMapping("/check")
	public ResponseEntity<?>  getIdCheck(@Valid @RequestBody UserVo.CRUD user, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return userService.getIdCheck(user);
	}

	
	@PostMapping("/manage")
	public ResponseEntity<?>  insertUserManage(@Valid @RequestBody UserVo.CRUD user, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return userService.userManage(user);
		
	}
	 
	@PostMapping("/manage/insert")
	public ResponseEntity<?>  insertUserManage(@Valid @RequestBody UserVo.Table user, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return userService.insertUserManage(user);
	}

	@PostMapping("/manage/update")
	public ResponseEntity<?>  updateUserManage(@Valid @RequestBody UserVo.Table user, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return userService.updateUserManage(user);
	}
	
	@PostMapping("/manage/delete")
	public ResponseEntity<?>  deleteUserManage(@Valid @RequestBody UserVo.Table user, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return userService.deleteUserManage(user);
	}
}