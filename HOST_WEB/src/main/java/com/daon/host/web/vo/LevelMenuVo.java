package com.daon.host.web.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자
public class LevelMenuVo {
	

	
	@Data
	public static class CRUD{
		private String regType;
		private String id;
		private String level;
		private String menuList;
		private String insId;
		private Date insDate;
		private String updId;
		private Date updDate;
	}
	
}
