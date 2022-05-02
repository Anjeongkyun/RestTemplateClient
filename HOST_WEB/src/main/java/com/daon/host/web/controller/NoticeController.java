package com.daon.host.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	private final NoticeService noticeService;
	private final Response response;

    @PostMapping("/list")
    public ResponseEntity<?> getList() {
    	
        return noticeService.getList();
    }
	
    @PostMapping("/detail")
    public ResponseEntity<?> getDetail(@Validated @RequestBody Notice notice, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
    	
        String noticeNo = notice.getNoticeNo();
        
        return noticeService.getDetail(noticeNo);
    }
    
    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Validated @RequestBody Notice notice, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }    	
        
        return noticeService.insert(notice);
    }
    

}