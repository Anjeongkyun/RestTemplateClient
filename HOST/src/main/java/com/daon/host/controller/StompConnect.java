package com.daon.host.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Component;

import com.daon.host.ws.stomp.StompFrame;
import com.daon.host.ws.stomp_client.StompClient;
import com.daon.host.ws.stomp_client.StompMessageListener;
import com.daon.host.ws.stomp_client.StompSubscription;

@Component
public class StompConnect {
	private Logger logger = LoggerFactory.getLogger(StompConnect.class);
	LocalDateTime currentDateTime = LocalDateTime.now();
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	private String reviceMsg;
	private Map<String, String> stompHeader;
	private String stompBody;
	
	StompClient stompSocket;
	
//	@Autowired(required = false)
//    private SimpMessagingTemplate messagingTemplate;
	
	//웹소켓 연결하고 수신전용 topic subscribe 
	public boolean websocketConnect(String url, String userKey) {
		stompSocket = new StompClient(URI.create(url));
		String receiveId = userKey;
		
		// Wait for a connection to establish
		boolean connected;
		try {
			connected = stompSocket.connectBlocking();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		if (!connected) {
			logger.error("Failed to connect to the socket");
			return false;
		}
		// Subscribing to a topic once STOMP connection is established
		stompSocket.subscribe("/topic/zone/"+receiveId, new StompMessageListener() {
			
			//구독중인 사용자고유키로 메시지 전송받는 부분.
			@Override
			public void onMessage(StompFrame stompFrame) {
				stompHeader = stompFrame.getHeaders();
				stompBody = stompFrame.getBody();
				System.out.println("stompBody : "+stompBody);
				redisTemplate.opsForValue().set("RV:" + receiveId, stompBody);
				//수신메시지 처리방식 필요.
				//- DB저장(maria or redis)
				//- api 호출(web or app)
				//- 요청>응답처리. 예)내차찾기메뉴에서 버튼클릭 했다면, 메시지 받아가기.
				//- websocket send stompSocket.send("/app/zone/"+zoneId_R+"", "수신메시지 처리해야함.");
			}
		});
		return connected;
	}
		
	// 접속여부 확인후 구독처리.
	public boolean sendSubscribe(String zoneId, String message) {
		if(stompSocket.isClosed()) {
			logger.info("websocket closed ...");
			return false;
		}else {
			// Subscribing to a topic once STOMP connection is established
			stompSocket.subscribe("/topic/zone/"+zoneId, new StompMessageListener() {
				
				@Override
				public void onMessage(StompFrame stompFrame) {
					stompHeader = stompFrame.getHeaders();
					stompBody = stompFrame.getBody();
				}
			});
		}
		return true;
	}
	
	// 접속여부 확인후 메시지전송
	public boolean sendMessage(String topicId, String message) {
		if(stompSocket.isClosed()) {
			logger.info("websocket closed ...");
			return false;
		}else {
			stompSocket.send("/app/zone/"+topicId, message);
		}
		return true;
	}
	
	//구독자 전부 구독해제
	public boolean unSubscribe(String userKey) {
		if(stompSocket.isClosed()) {
			logger.info("websocket closed ...");
			return false;
		}else {
//			stompSocket.removeSubscription(null);
			stompSocket.removeAllSubscriptions("/topic/zone/"+userKey);
		}
		return true;
		
	}
	
	//웹소켓 접속 해제
	public boolean closeConnect(String userKey) {
		if(stompSocket.isClosed()) {
			logger.info("websocket closed ...");
			return false;
		}else {
			stompSocket.closeConnection(1006, userKey);
		}
		return true;
		
	}
	
}
