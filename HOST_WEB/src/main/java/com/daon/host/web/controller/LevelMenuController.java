package com.daon.host.web.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.dto.Response;
import com.daon.host.web.service.LevelMenuService;
import com.daon.host.web.vo.LevelMenuVo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import utils.Helper;

@Slf4j
@RestController // rest api 를 사용하기 위함
//@CrossOrigin(origins = "*", allowedHeaders = "*")

@Data
@RequestMapping("/internal/davis/web/level/menu")
public class LevelMenuController {

	/*
	 * jpa flow Controller -> Service -> Repository(JpaRepository 상속 인터페이스) mybatis
	 * flow Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML) 참고:
	 * https://blog.jiniworld.me/55
	 */

	private final LevelMenuService levelMenuService;
	private final Response response;
	
	@PostMapping("/list")
	public ResponseEntity<?>  getUser(@Valid @RequestBody LevelMenuVo.CRUD levelMenu, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return levelMenuService.getList(levelMenu);
	}
	
	@PostMapping("/manage")
	public ResponseEntity<?>  setMenu(@Valid @RequestBody LevelMenuVo.CRUD levelMenu, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return levelMenuService.setLevel(levelMenu);
		
	}
}