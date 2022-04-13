package com.daon.host.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DefaultMessage {
	
	String topic_name;		//토픽네임
	String user_id; 		//random UUID
	String method_type; 	//요청구분 : GET/POST/PUT/DELETE/PATCH
	String program_type; 	//용도구분 : 웹/앱, 사용요도
	
	String msg1;			//송수신 데이터1 
	String msg2;			//송수신 데이터2
	String msg3;			//송수신 데이터3
	String msg4;			//송수신 데이터4
	String msg5;			//송수신 데이터5
	String msg6;			//송수신 데이터6
	String msg7;			//송수신 데이터7
	String msg8;			//송수신 데이터8
	String msg9;			//송수신 데이터9
	String msg10;			//송수신 데이터10
}
