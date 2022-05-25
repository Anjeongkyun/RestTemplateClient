package com.daon.host.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.dto.Response;
import com.daon.host.web.service.ParkService;
<<<<<<< .mine







=======
import com.daon.host.web.service.LoginService;
import com.daon.host.web.service.NoticeService;
import com.daon.host.web.service.ParkService;
import com.daon.host.web.vo.LoginVo;
import com.daon.host.web.vo.Notice;
import com.daon.host.web.vo.ParkVo;
import com.daon.host.web.vo.UserProfile;
>>>>>>> .theirs

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // rest api 를 사용하기 위함
@RequiredArgsConstructor
@RequestMapping("/park")
public class ParkController {

	private final ParkService parkService;
	private final Response response;
	
	/*
	 * jpa flow Controller -> Service -> Repository(JpaRepository 상속 인터페이스) mybatis
	 * flow Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML) 참고:
	 * https://blog.jiniworld.me/55
	 */

	@PostMapping("/list")
	public ResponseEntity<?> getList() {
		return parkService.getList();
	}
<<<<<<< .mine
	






=======
	public ResponseEntity<?>  getPark(@Valid @RequestBody ParkVo.Park park, Errors errors) {
		
		 if (errors.hasErrors()) {
	            return response.failInvalidFields(Helper.refineErrors(errors));
	        }
		return parkService.getPark(park);
	}
>>>>>>> .theirs

	
}