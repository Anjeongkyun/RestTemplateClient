package com.daon.host.web.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//모델의 역할
@Getter  // 게터 생성,  모든 필드에 접근자와 설정자가 자동으로 생성
@Setter  // 세터 생성,  모든 필드에 접근자와 설정자가 자동으로 생성
//@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
//@RequiredArgsConstructor  //final이나 @NonNull인 필드 값만 파라미터로 받는 생성자
@AllArgsConstructor  //모든 필드 값을 파라미터로 받는 생성자
public class UserProfile {
	private String id; 
	private String name; 
	private String phone;
	private String add;
}
