package com.daon.host.web.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.LoginMapper;
import com.daon.host.web.service.LoginService;
import com.daon.host.web.service.NoticeService;
import com.daon.host.web.vo.LoginVo;
import com.daon.host.web.vo.Notice;
import com.daon.host.web.vo.UserProfile;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import utils.Helper;

@Slf4j
@RestController // rest api 를 사용하기 위함
//@CrossOrigin(origins = "*", allowedHeaders = "*")

@Data
@RequestMapping("/internal/davis/web/login")
public class LoginController {

	/*
	 * jpa flow Controller -> Service -> Repository(JpaRepository 상속 인터페이스) mybatis
	 * flow Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML) 참고:
	 * https://blog.jiniworld.me/55
	 */

	private final LoginService loginService;
	private final Response response;

	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> getLogin(@Valid @RequestBody LoginVo.Login login, Errors errors) {

		if (errors.hasErrors()) {
			return response.failInvalidFields(Helper.refineErrors(errors));
		}
		// given
		/*
		 * String rawPassword = "12345678";
		 * 
		 * // when String encodedPassword = passwordEncoder.encode(rawPassword);
		 * 
		 * // then
		 * 
		 * assertAll( () -> assertNotEquals(rawPassword, encodedPassword), () ->
		 * assertTrue(passwordEncoder.matches(rawPassword, encodedPassword)) );
		 * 
		 */

		return loginService.getLogin(login);
	}

}