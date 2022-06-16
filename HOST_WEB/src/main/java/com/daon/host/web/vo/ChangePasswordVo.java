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
public class ChangePasswordVo {

// db
//	login_id
//	login_pw
//	member_nm
//	member_role
//	member_phone
//	use_yn
//	ins_id
//	ins_date
//	upd_id
//	upd_date

	
	
	@Data
	public static class Table{
		
		private String loginId; // 아이디
		private String loginPw; // 비밀번호
		private String memberMm;
		private String memberRole;
		private String memberPhone; // 전화번호
		private String useYn; // 사용여부
		private String insId; // 입력자
		private Date insDate; // 입력시간
		private String updId; // 수정자
		private Date updDate;// 수정시간
	}
	
	@Data
	public static class ChangePassword {
		@NotEmpty(message = "아이디는 필수 입력값입니다.")
		//@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
		private String id; // 아이디

		@NotEmpty(message = "초기화여부는 필수 입력값입니다.")
		private String firstRun;
		
		@NotEmpty(message = "비밀번호는 필수 입력값입니다.")
		//@Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[a-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 소문자, 숫자, 특수문자를 사용하세요.")
		private String password; // 비밀번호
		
		@NotEmpty(message = "새 비밀번호는 필수 입력값입니다.")
		private String newPassword;
		
	}
}
