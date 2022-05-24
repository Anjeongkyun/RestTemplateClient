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
public class CmmnVo {
	
	
	
	/*
cm_cd
gr_cd
sub_kind_cd
sub_cd
cd_nm 
use_yn

cmCd
grCd
subKindCd
subCd
cdNm 
useYn

	 */
	@Data
	public static class CmmnCode{
		private String cmCd;
		private String grCd;
		private String subKindCd;
		private String subCd;
		private String cdNm ;
		private String useYn;
	}
	
	@Data
	public static class CmmnBase{
		private String reset_password;
	}
		
	
}
