package com.daon.host.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.dto.Response;
import com.daon.host.web.service.NoticeService;
import com.daon.host.web.vo.Notice;

import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/list")
    @ApiOperation(value= "공지사항 전체조회", notes="공지사항의 데이터를 모두 조회한다.")
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

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@Validated @RequestBody Notice notice, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }    	        
        return noticeService.delete(notice);
    }
    
    @PostMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody Notice notice, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }    	        
        return noticeService.update(notice);
    }
    

}