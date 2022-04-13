package com.daon.host.local.controller;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {
	
	@Autowired
	StompConnect sc;
	
    //local server GET  
    //로컬서버에서 사용자 메시지 푸시
	//gbn = C:내차위치 / H : 세대주이름 / I : 입차
    @GetMapping("/push/{gbn}/{aptId}/{loginId}")
    public ResponseEntity<?> selectLocalInfo(@PathVariable String gbn, @PathVariable String aptId, @PathVariable String loginId){
    	String str = gbn+","+aptId+","+loginId+",";
    	boolean ret = sc.receiveMsg(str);
    	if(ret) {
    		return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    	}
    	return ResponseEntity.ok(HttpStatus.OK);
    }
}