package com.daon.host.web.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Notice {

	private String noticeNo; // 공지번호
	private String aptId; // 단지코드
	private String noticeWriter; // 공지작성자
	private String noticeContent; // 공지내용
	private String noticeTitle; // 공지제목
	private String remark; // 비고
	private String insId; // 입력자
	private Date insDate; // 입력시간
	private String updId; // 수정자
	private Date updDate; // 수정시간

}
