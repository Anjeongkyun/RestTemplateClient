package com.daon.host.vo;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserKey {
	
	@NotEmpty(message = "loginId 를 입력해주세요.")
	private String loginId;
	
	@NotEmpty(message = "accessToken 을 입력해주세요.")
    private String accessToken;

    @NotEmpty(message = "refreshToken 을 입력해주세요.")
    private String refreshToken;
    
	
}
