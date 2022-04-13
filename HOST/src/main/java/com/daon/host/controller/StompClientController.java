package com.daon.host.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PropertySource("classpath:websocketInfo.properties")
@RequestMapping("/ws")
public class StompClientController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StompConnect conn;
	
	@Value("${websocket.url}")
	private String url;
	
	//stomp client param add
	//1. connect 체크 분기처리
	//2. subscribe 체크 분기처리
	//3. 위의 조건 패스하면 바로 메시지 전송
	//4. topic sendId/reciveId 구분해서 전송
	
	//로그인하고 데이터 수신받을 소켓연결+수신topic subscribe
	@GetMapping("/connect/{zoneId}")
//	@RequestMapping(value="/conn")
	public ResponseEntity<String> websocketConnect(@PathVariable String zoneId) throws Exception{
		try {
			conn.websocketConnect(url,zoneId);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	//param - zoneId subscribe
	//소켓연결여부 확인하고 송신topic subscribe
	@GetMapping("/sendSubscribe/{zoneId}")
	public ResponseEntity<String> sendSubscribe(@PathVariable String zoneId, @RequestBody String message) throws Exception{
		try {
			conn.sendSubscribe(zoneId,message);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		return new ResponseEntity<String>(HttpStatus.OK);
	 }
	
	//소켓연결여부 확인하고 메시지 전송처리.
	@GetMapping("/sendMessage/{zoneId}")
	public ResponseEntity<String> sendMessage(@PathVariable String zoneId, @RequestBody String message) throws Exception{
		try {
			conn.sendMessage(zoneId,message);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		return new ResponseEntity<String>(HttpStatus.OK);
	 }
	
	//구독해제
	@GetMapping("/unSubscribe/{zoneId}")
	public ResponseEntity<String> unSubscribe(@PathVariable String zoneId) throws Exception{
		try {
			conn.unSubscribe(zoneId);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		return new ResponseEntity<String>(HttpStatus.OK);
	 }
	
	//웹소켓 접속해제
	@GetMapping("/closeConnect/{zoneId}")
	public ResponseEntity<String> closeConnect(@PathVariable String zoneId) throws Exception{
		try {
			conn.closeConnect(zoneId);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	 
}
