package com.daon.host.web.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자
public class ParkVo {
	
	@Data
	public static class Table{
		
		private String updId;
		private String updDate;
		private String repNum;
		private String projectId;
		private String managerNm;
		private String longitude;
		private String latitude;
		private String insId;
		private String insDate;
		private String aptNm;
		private String aptId;
	}
	
	@Data
	public static class Park {
	
		private String parkSeq; // 단지 구별을 위한 고유값(ex. AAA00 = 전체)

	
	}
}
