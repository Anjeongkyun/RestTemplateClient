package com.daon.host.web.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.dto.Response;
import com.daon.host.web.service.MenuService;
import com.daon.host.web.vo.MenuVo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import utils.Helper;

@Slf4j
@RestController // rest api 를 사용하기 위함
//@CrossOrigin(origins = "*", allowedHeaders = "*")

@Data
@RequestMapping("/internal/davis/web/menu")
public class MenuController {

	/*
	 * jpa flow Controller -> Service -> Repository(JpaRepository 상속 인터페이스) mybatis
	 * flow Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML) 참고:
	 * https://blog.jiniworld.me/55
	 */

	private final MenuService menuService;
	private final Response response;
	
	@PostMapping("/list")
	public ResponseEntity<?>  getList(@Valid @RequestBody MenuVo.CRUD menu, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return menuService.getList(menu);
	}
	

	@PostMapping("/list/show")
	public ResponseEntity<?>  getListShowYN(@Valid @RequestBody MenuVo.CRUD menu, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return menuService.getListShowYN(menu);
	}

	@PostMapping("/manage")
	public ResponseEntity<?>  setMenu(@Valid @RequestBody MenuVo.CRUD user, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return menuService.setMenu(user);
		
	}
	 
}