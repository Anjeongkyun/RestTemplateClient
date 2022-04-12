package com.daon.host.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.dto.UserRequestDto;
import com.daon.host.service.ApiService;
import com.daon.host.vo.BasicInfo;
import com.daon.host.vo.MobileUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {
	
	/* jpa flow
	 * Controller -> Service -> Repository(JpaRepository 상속 인터페이스)
	 * mybatis flow
	 * Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML)
	 참고: https://blog.jiniworld.me/55 [hello jiniworld]
	 * */
	
	private final ApiService apiService;
	
	//로그인 jwt x
    @GetMapping("/user/{loginId}")
    public ResponseEntity<?> getUser(@PathVariable String loginId){
    	return apiService.selectUser(loginId);
    }
    
    //회원가입
    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody MobileUser user){
    	return apiService.saveUser(user); 
    }
    
    //회원정보 수정
    @PutMapping("/user/{loginId}")
    public ResponseEntity<?> updateUser(@PathVariable String loginId, @RequestBody MobileUser user){
    	return apiService.updateUser(loginId,user); 
    }
    
    //회원정보 삭제
    @DeleteMapping("/user/{loginId}")
    public ResponseEntity<?> deleteUser(@PathVariable String loginId){
    	return apiService.deleteUser(loginId); 
    }
    
    //비밀번호 변경
    @PatchMapping("/user")
    public ResponseEntity<?> updatePwd(@RequestBody BasicInfo.UserPassword userPw){
    	return apiService.updatePwd(userPw); 
    }
    
    //mariaDb POST
    //사용자 고유키 생성
//    @PostMapping("/userKey")
//	public ResponseEntity<?> setUserKey(@RequestBody UserRequestDto.UserKey userKey){
//		System.out.println("userKey : "+userKey);
//		return apiService.setUserKey(userKey);
//	}
    
    //사용자 고유키 조회
    @GetMapping("/userKey/{loginId}")
    public ResponseEntity<?> getUserKey(@PathVariable String loginId){
    	return apiService.getUserKey(loginId);
    }
    
    //단지 정보 조회
    @GetMapping("/complex")
    public ResponseEntity<?> selectComplex(){
    	return apiService.selectComplex();
    }
    
    //단지(소속) 기초정보 조회
    @GetMapping("/myComplex/{aptId}")
    public ResponseEntity<?> selectMyComplex(@PathVariable String aptId){
    	return apiService.selectMyComplex(aptId);
    }
    
    //단지 동호정보 조회
    @GetMapping("/dongho/{aptId}")
    public ResponseEntity<?> selectDongho(@PathVariable String aptId){
    	return apiService.selectDongho(aptId);
    }
    
    //단지+동+호 세대주 이름 조회
    @GetMapping("/houseHold/{aptId}/{dong}/{ho}")
    public ResponseEntity<?> selectHouseHold(@PathVariable String aptId, @PathVariable String dong, @PathVariable String ho){
    	return apiService.selectHouseHold(aptId,dong,ho);
    }
    
    //사용자 차량정보 조회
    @GetMapping("/car/{aptId}/{dong}/{ho}")
    public ResponseEntity<?> selectUserCar(@PathVariable String aptId, @PathVariable String dong, @PathVariable String ho){
    	return apiService.selectUserCar(aptId,dong,ho);
    }
    
    //사용자 차량정보 등록
    @PostMapping("/car")
    public ResponseEntity<?> saveUserCar(@RequestBody BasicInfo.UserCar userCar){
    	return apiService.insertUserCar(userCar);
    }
    
    //공지사항 조회
    @GetMapping("/notice/{aptId}")
    public ResponseEntity<?> selectNotice(@PathVariable String aptId){
    	return apiService.selectNotice(aptId);
    }
    
    //출입증 조회
    @GetMapping("/pass")
    public ResponseEntity<?> selectPass(@PathVariable String passMobile){
    	return apiService.selectPass(passMobile); 
    }
    
    //출입증 등록
    @PostMapping("/pass")
    public ResponseEntity<?> insertPass(@RequestBody BasicInfo.ComplexPass cmpxPass){
    	return apiService.insertPass(cmpxPass); 
    }
    
}
