package com.daon.host.web.vo;

import lombok.Data;

public class UserVo{

	/*
	login_id
	login_pw
	member_nm
	member_role
	member_phone
	use_yn
	ins_id
	ins_date
	upd_id
	upd_date
	level_cd
	first_run
	
	 loginId
	 loginPw
	 memberNm
	 memberRole
	 memberPhone
	 useYn
	 levelCd
	 firstRun
	 insId
	 insDate
	 updId
	 updDate
	 */
	
	@Data
	public static class CRUD{
		private String regType;
		private String loginId;
		private String loginPw;
		private String memberNm;
		private String memberRole;
		private String memberPhone;
		private String useYn;
		private String levelCd;
		private String firstRun;
		private String insId;
		private String insDate;
		private String updId;
		private String updDate;
	}
	
	
	@Data
	public static class Table{
		private String loginId;
		private String loginPw;
		private String memberNm;
		private String memberRole;
		private String memberPhone;
		private String useYn;
		private String levelCd;
		private String firstRun;
		private String insId;
		private String insDate;
		private String updId;
		private String updDate;
	}
	
	@Data
	public static class User {
		private String loginId;  //통합관리 ID
		private String memberNm; //통합관리 사용자 이름 부분검색
	}

}
