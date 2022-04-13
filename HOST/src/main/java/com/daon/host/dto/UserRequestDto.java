package com.daon.host.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserRequestDto {

    @Getter
    @Setter
    public static class SignUp {
    	
    	/*********************** tb_o_mobuser ***********************/
        @NotEmpty(message = "이메일은 필수 입력값입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        private String email;

        @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[a-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 소문자, 숫자, 특수문자를 사용하세요.")
//        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;
        
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
    	/*********************** tb_o_mobuser ***********************/
    	
    	/*********************** tb_o_terms_history ***********************/
    	private String terms_yn;
    	/*********************** tb_o_terms_history ***********************/
    }

    @Getter
    @Setter
    public static class Login {
        @NotEmpty(message = "이메일은 필수 입력값입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        private String email;

        @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }

    @Getter
    @Setter
    public static class Reissue {
        @NotEmpty(message = "accessToken 을 입력해주세요.")
        private String accessToken;

        @NotEmpty(message = "refreshToken 을 입력해주세요.")
        private String refreshToken;
    }

    @Getter
    @Setter
    public static class Logout {
        @NotEmpty(message = "잘못된 요청입니다.")
        private String accessToken;

        @NotEmpty(message = "잘못된 요청입니다.")
        private String refreshToken;
    }
    
    @Getter
    @Setter
//    @AllArgsConstructor
    public static class UserKey {
    	
    	@NotEmpty(message = "loginId 를 입력해주세요.")
    	private String loginId;
    	
    	@NotEmpty(message = "accessToken 을 입력해주세요.")
        private String accessToken;

        @NotEmpty(message = "refreshToken 을 입력해주세요.")
        private String refreshToken;
    	
    }
}
