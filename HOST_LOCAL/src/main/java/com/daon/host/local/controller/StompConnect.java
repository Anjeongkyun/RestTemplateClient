package com.daon.host.local.controller;

import java.net.URI;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Component;

import com.daon.host.local.stomp.StompFrame;
import com.daon.host.local.stomp_client.StompClient;
import com.daon.host.local.stomp_client.StompMessageListener;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:websocketInfo.properties")
public class StompConnect implements ApplicationListener<ContextRefreshedEvent> {
	private Logger logger = LoggerFactory.getLogger(StompConnect.class);

	LocalDateTime currentDateTime = LocalDateTime.now();

	private String payLoad;
	
	@Autowired(required = false)
    private SimpMessagingTemplate template;
	
	@Value("${websocket.url}")
	private String url;
	
	@Value("${websocket.topic}")
	private String zoneId;
	
//	@PostConstruct
//	public void init() {
//		ws_connect(url, zoneId);
//	}
	
	StompClient stompSocket;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		ws_connect(url, zoneId);
	}
	
	public void ws_connect(String url, String zoneId) {
		stompSocket = new StompClient(URI.create(url));

        // Wait for a connection to establish
        boolean connected;
        try {
            connected = stompSocket.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        if (!connected) {
            logger.error("Failed to connect to the socket");
            return;
        }
        // Subscribing to a topic once STOMP connection is established
        stompSocket.subscribe("/topic/zone/"+zoneId, new StompMessageListener() {

        	@Override
            public void onMessage(StompFrame stompFrame) {
        		payLoad = stompFrame.getBody();
//        		System.out.println("stompFrame header client-HOST_L :  "+stompFrame.getHeaders());
//                System.out.println("Server message client-HOST_L : " + stompFrame.getBody());
//                System.out.println("Server receive time client-HOST_L : " + currentDateTime);
                
                //server send message -> server receive message
                receiveMsg(payLoad);
//                Disconnect
//                stompSocket.close();
            }
        });
        // Sending JSON message to a server
//        stompSocket.send("/app/zone/"+zoneId, "Local server connecting");
	}
	
	//sendId : 사용자 고유키
	//payload : gbn+aptId+loginId
	//gbn = C:내차위치 / H : 세대주이름 / I : 입차
	public boolean receiveMsg(String payLoad) {
		try {
			String[] rcMsg = payLoad.split(",");
			String gbn = rcMsg[0];
			String aptId = rcMsg[1];
			String loginId = rcMsg[2];
			String rtnMsg;
			
			if(gbn.equals("C")) {
				//초음파서버에서 내차위치 받아옴
				System.out.println("param-gbn :"+gbn+"/ 내차위치조회 : ");
				rtnMsg = "B1 1A12";
			}else if(gbn.equals("H")) {
				//초음파서버에서 세대주이름 받아옴
				System.out.println("param-gbn :"+gbn+"/ 세대주이름 : ");
				rtnMsg = "곰쓰";
			}else if(gbn.equals("I")) {
				//초음파서버에서 사용자에게 메시지 푸시
				System.out.println("param-gbn :"+gbn+"/ 입차 : ");
				rtnMsg = "주차완료. 해당위치 B2 2A2Q";
			}else{
				//초음파서버에서 기본값.
				System.out.println("param-gbn :"+gbn+"/ 기본값 : ");
				rtnMsg = "default message";
			}
			
			stompSocket.send("/app/zone/"+loginId, rtnMsg);
			System.out.println("msg receive ok : "+rtnMsg);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
}
