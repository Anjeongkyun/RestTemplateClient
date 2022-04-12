package com.daon.host.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.dto.Response;
import com.daon.host.dto.UserRequestDto;
import com.daon.host.jwt.JwtTokenProvider;
import com.daon.host.service.UsersService;
import com.daon.host.utils.Helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UsersController {
	
	/* jpa flow
	 * Controller -> Service -> Repository(JpaRepository 상속 인터페이스)
	 * mybatis flow
	 * Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML)
	 참고: https://blog.jiniworld.me/55
	 * */

    private final UsersService usersService;
    private final Response response;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Validated UserRequestDto.SignUp signUp, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return usersService.signUp(signUp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated UserRequestDto.Login login, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return usersService.login(login);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@Validated UserRequestDto.Reissue reissue, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return usersService.reissue(reissue);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Validated UserRequestDto.Logout logout, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return usersService.logout(logout);
    }
    
    //local server GET  
    //로컬서버 데이터 요청.
    @GetMapping("/infomation/{gbn}/{loginId}/{aptId}")
    public ResponseEntity<?> selectLocalInfo(@PathVariable String gbn, @PathVariable String loginId, @PathVariable String aptId){
    	return usersService.selectLocalInfo(gbn,loginId, aptId);
    }
}