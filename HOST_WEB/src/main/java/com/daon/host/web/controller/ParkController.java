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
import com.daon.host.web.service.ParkService;
import com.daon.host.web.vo.ParkVo;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import utils.Helper;

@Slf4j
@RestController // rest api 를 사용하기 위함
@RequiredArgsConstructor
@RequestMapping("/parks")
public class ParkController {

	private final ParkService parkService;
	private final Response response;
	
	/*
	 * jpa flow Controller -> Service -> Repository(JpaRepository 상속 인터페이스) mybatis
	 * flow Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML) 참고:
	 * https://blog.jiniworld.me/55
	 */

    @ApiOperation(value= "단지 정보 전체조회",
        	notes="단지 정보를 모두 조회한다.")
	@GetMapping
	public ResponseEntity<?> getList() {
		return parkService.getList();
	}

    @ApiOperation(value= "단지 정보 상세조회",
	notes="단지 상세 데이터를 조회한다.")    
    @GetMapping("/{aptId}")
    public ResponseEntity<?> getDetail(@PathVariable String aptId) {
        
        return parkService.getDetail(aptId);
    }
    
    @ApiOperation(value= "단지 정보 생성",
	notes="단지 정보를 생성한다.")  
    @PostMapping
    public ResponseEntity<?> insert(@Validated @RequestBody ParkVo parkVo, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }    	
        return parkService.insert(parkVo);
    }
    
    @ApiOperation(value= "단지 정보 수정",
	notes="단지 상세 데이터를 조회한다.")
    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody ParkVo parkVo, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }    	        
        return parkService.update(parkVo);
    }
    
    @ApiOperation(value= "단지 정보 삭제",
	notes="단지 정보 데이터를 삭제한다.")  
    @DeleteMapping("/{aptId}")
    public ResponseEntity<?> delete(@PathVariable String aptId) {
    	
        return parkService.delete(aptId);
    }
	
}