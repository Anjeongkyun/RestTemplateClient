package com.daon.host.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/* MariaDb - daondb - table */
public class BasicInfo {
	
	@Getter
	@Setter
	//단지정보 - tb_o_complex
	public static class Complex {
		private String apt_id;
		private String apt_nm;
	}
	@Getter
	@Setter
	//소속단지정보 - tb_o_complex
	public static class MyComplex {
		private String apt_id;
		private String project_id;
		private String apt_nm;
		private String apt_address;
		private String rep_num;
		private String manager_nm;
		private String latitude;
		private String longitude;
	}
	@Getter
	@Setter
	//동호 - tb_o_dongho
	public static class Dongho{
		private String apt_id;
		private String dong;
		private String ho;
	}
	@Getter
	@Setter
	//이용약관 - tb_o_terms
	public static class Terms{
		private String terms_id;
		private String terms_nm;
		private String terms_cont;
		private String terms_req;
		private String remk;
		
	}
	
	@Getter
	@Setter
	//사용자정보 - tb_o_mobuser
	public static class UserPassword{
		private String login_id;
		private String login_pw;
	}
	
	@Getter
	@Setter
	//사용자차량정보 - tb_o_user_car
	public static class UserCar{
		private String apt_id;
		private String dong;
		private String ho;
		private String car_no;
		private String remk;
	}
	
	@Getter
	@Setter
	//공지사항 - tb_o_notice
	public static class Notice{
		private String notice_no;
		private String apt_id;
		private String notice_writer;
		private String notice_cont;
		private String remk;
	}
	
	@Getter
	@Setter
	//출입증 - tb_o_complex_pass
	public static class ComplexPass{
		private String pass_seq;
		private String login_id;
		private String pass_cd;
		private String pass_nm;
		private String pass_mobile;
		private String pass_s_date;
		private String pass_e_date;
		private String pass_carno;
		private String pass_yn;
		private String remk;
	}
}
