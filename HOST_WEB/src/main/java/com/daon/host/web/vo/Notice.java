package com.daon.host.web.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice {

	private String notice_no; // 공지번호
	private String apt_id; // 단지코드
	private String notice_writer; // 공지작성자
	private String notice_cont; // 공지내용
	private String notice_title; // 공지제목
	private String remk; // 비고
	private String ins_id; // 입력자
	private Date ins_date; // 입력시간
	private String upd_id; // 수정자
	private Date upd_date; // 수정시간

}
