package com.daon.host.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MobileUser {
	
	@NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
	private String login_id;
	
	@NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[a-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 소문자, 숫자, 특수문자를 사용하세요.")
	private String login_pw;
	
	@NotEmpty(message = "단지코드는 필수 입력값입니다.")
	private String apt_id; 
	
	private String member_nm; 
	private String member_mobile; 
	private String blue_id; 
	private String dong; 
	private String ho; 
	private String household_yn; 
	private String car_no;
	private String car_type;
	private int user_active;
	private String ins_id;
	private String upd_id;
	
}
