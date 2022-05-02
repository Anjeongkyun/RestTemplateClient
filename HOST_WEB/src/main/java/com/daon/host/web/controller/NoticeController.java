package com.daon.host.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.dto.Response;
import com.daon.host.web.service.NoticeService;
import com.daon.host.web.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import utils.Helper;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	
	/* jpa flow
	 * Controller -> Service -> Repository(JpaRepository 상속 인터페이스)
	 * mybatis flow
	 * Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML)
	 참고: https://blog.jiniworld.me/55
	 * */
	
	private final NoticeService noticeService;
	private final Response response;
	
    @GetMapping("/list/{noticeNo}")
    public ResponseEntity<?> selectNo(@Validated Notice notice, @PathVariable String noticeNo,
    		Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
    	
        return noticeService.selectNo(noticeNo);
    }
    
//    @GetMapping("/list")
//    public String signUp() {
//
//        return "index.html";
//    }

}