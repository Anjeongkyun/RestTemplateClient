package com.daon.host.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.dto.Response;
import com.daon.host.web.service.NoticeService;
import com.daon.host.web.vo.NoticeVo;

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

    @ApiOperation(value= "공지사항 전체조회",
    	notes="공지사항의 데이터를 모두 조회한다.")
    @GetMapping
    public ResponseEntity<?> getList() {
    	
        return noticeService.getList();
    }
    
    @ApiOperation(value= "공지사항 상세조회",
	notes="공지사항의 상세 데이터를 조회한다.")    
    @GetMapping("/{noticeNo}")
    public ResponseEntity<?> getDetail(@PathVariable String noticeNo) {
        
        return noticeService.getDetail(noticeNo);
    }
    
    @ApiOperation(value= "공지사항 작성",
	notes="공지사항을 작성한다.")  
    @PostMapping
    public ResponseEntity<?> insert(@Validated @RequestBody NoticeVo notice, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }    	
        return noticeService.insert(notice);
    }

    @ApiOperation(value= "공지사항 삭제",
	notes="공지사항 데이터를 삭제한다.")  
    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<?> delete(@PathVariable String noticeNo) {
    	
        return noticeService.delete(noticeNo);
    }
    
    @ApiOperation(value= "공지사항 수정",
	notes="공지사항 데이터를 수정한다.")  
    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody NoticeVo notice, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }    	        
        return noticeService.update(notice);
    }
    

}