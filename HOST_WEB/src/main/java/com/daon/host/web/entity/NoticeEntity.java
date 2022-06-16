package com.daon.host.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="tb_o_notice")
public class NoticeEntity extends BaseTimeEntity{

	@Id
	@Column(name = "notice_no")
	private String noticeNo; // 공지번호
	
	@Column(name = "apt_id")
	private String aptId; // 단지코드
	
	@Column(name = "notice_writer")
	private String noticeWriter; // 공지작성자
	
	@Column(name = "notice_content")
	private String noticeContent; // 공지내용
	
	@Column(name = "notice_title")
	private String noticeTitle; // 공지제목
	
	@Column(name = "remk")
	private String remark; // 비고
	
	@Column(name = "ins_id")
	private String insId; // 입력자
	
//	@CreationTimestamp
//	@Column(name = "ins_date")
//	private LocalDateTime insDate; // 입력시간
	
	@Column(name = "upd_id")
	private String updId; // 수정자
	
//	@UpdateTimestamp
//	@Column(name = "upd_date")
//	private LocalDateTime updDate; // 수정시간
}
