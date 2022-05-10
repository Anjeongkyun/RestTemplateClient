package com.daon.host.web.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daon.host.web.dto.Response;
import com.daon.host.web.mapper.LoginMapper;
import com.daon.host.web.service.LoginService;
import com.daon.host.web.service.NoticeService;
import com.daon.host.web.vo.LoginVo;
import com.daon.host.web.vo.Notice;
import com.daon.host.web.vo.UserProfile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import utils.Helper;

//@Slf4j
@RestController // rest api 를 사용하기 위함

@RequiredArgsConstructor
@RequestMapping("/a")
public class MainController {

	/*
	 * jpa flow Controller -> Service -> Repository(JpaRepository 상속 인터페이스) mybatis
	 * flow Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML) 참고:
	 * https://blog.jiniworld.me/55
	 */

	private Map<String, UserProfile> userMap;

	@PostConstruct // 클래스 인스턴스를 만든직후 초기화됨
	public void init() {
		userMap = new HashMap<String, UserProfile>();
		userMap.put("1", new UserProfile("1", "오희진", "111-1111", "서울시1"));
		userMap.put("2", new UserProfile("2", "안정균", "111-1112", "서울시2"));
		userMap.put("3", new UserProfile("3", "다온텍", "111-1113", "서울시3"));
	}

	// id를 인자로 받는
	@GetMapping("/user/{id}")
	public UserProfile getUserProfile(@PathVariable("id") String id) {
		//@PathVariable("id")  url 파라미터로 받은 {id} 인식
		return userMap.get(id);
	}

	// 전체 유저
	@GetMapping("")
	public List<UserProfile> getUserProfileList() {
		return new ArrayList<UserProfile>(userMap.values());
	}

	@PutMapping("/user/{id}")
	public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("address") String address) {
		UserProfile userProfile = new UserProfile(id, name, phone, address);
		userMap.put(id, userProfile);
	}

	@PostMapping("/user/{id}")
	public void postUserprofile(@PathVariable("id") String id, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("address") String address) {
		UserProfile userProfile = userMap.get(id);
		userProfile.setName(name);
		userProfile.setPhone(phone);
		userProfile.setAdd(address);
		
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUserprofile(@PathVariable("id") String id) {
		userMap.remove(id);
		
	}
	@Autowired
	private LoginMapper loginMapper; 
	
	@PostMapping("/testapi")
	public int post(@RequestBody LoginVo loginVo) {
		return loginMapper.putLogin1(loginVo);
	}
	
	@PostMapping("/testapi1")
	public ResponseEntity<?>  post1(@RequestBody LoginVo loginVo) {
		return loginService.putLogin1(loginVo);
	}

	@PostMapping("/testapi/all")
	public ResponseEntity<?>  postAll(@RequestBody LoginVo loginVo) {
		return loginService.putLoginAll(loginVo);
	}
	
//	// id를 인자로 받는
//	@GetMapping("/{id}")
//	public LoginVo getLogin(@PathVariable("id") String id) {
//		//@PathVariable("id")  url 파라미터로 받은 {id} 인식
//		return new LoginVo;
//	}
//
//	// 전체 유저
//	@GetMapping("/all")
//	public List<LoginVo> getLoginList() {
//		return new LoginVo;
//	}
//	
//	

	@PutMapping("/{id}")
	public void putLogin(@PathVariable("loginId") String loginId, @RequestParam("loginPw") String loginPw,
			@RequestParam("memberMm") String memberMm, @RequestParam("memberRole") String memberRole, 
			@RequestParam("memberPhone") String memberPhone, @RequestParam("useYn") String useYn, 
			@RequestParam("insId") String insId, @RequestParam("insDate") String insDate) {
		
	}

	@PostMapping("/{id}")
	public void postLogin(@PathVariable("loginId") String loginId, @RequestParam("loginPw") String loginPw,
			@RequestParam("memberMm") String memberMm, @RequestParam("memberRole") String memberRole, 
			@RequestParam("memberPhone") String memberPhone, @RequestParam("useYn") String useYn, 
			@RequestParam("updId") String updId,@RequestParam("updDate") String updDate) {
		
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteLogin(@PathVariable("id") String id) {
		userMap.remove(id);
		
	}
	
	
	private final NoticeService noticeService;
	private final LoginService loginService;
	private final Response response;

	@RequestMapping(value = "/api/test", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public String getApiTest() {
		return "{\"result\":\"ok\"}";
	}

	@RequestMapping(value = "/api/test", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String getApiTest2() {
		return "{\"result\":\"ok123123\"}";
	}

	

	@PostMapping("/sample/post-mapping")
	@ResponseBody
	public String samplePostMapping() {
		return "this is post mapping example";
	}

	// 출입증 등록
	@PostMapping("/pass1234")
	@ResponseBody
	public String insertPass1() {

		System.out.println("test");
		return "123123";
	}

	

	@GetMapping("/list")
	public ResponseEntity<?> selectNo45(@Validated Notice notice, @PathVariable String noticeNo, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}

		return noticeService.selectNo(noticeNo);
	}

	@GetMapping("/list/{noticeNo}")
	public ResponseEntity<?> selectNo54(@Validated Notice notice, @PathVariable String noticeNo, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}

		return noticeService.selectNo(noticeNo);
	}

	@GetMapping("/test")
	public ResponseEntity<?> selectNo4(@Validated Notice notice, @PathVariable String noticeNo, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}

		return loginService.selectNo1(noticeNo);
	}

	@PostMapping()
	public ResponseEntity<?> selectNo2(@Validated LoginVo loginVo, @RequestBody LoginVo loginVo1, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}

		return loginService.selectNo(loginVo1);
	}

	@PostMapping("/")
	public ResponseEntity<?> selectNo1(@Validated LoginVo loginVo, @RequestBody LoginVo loginVo1, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}

		return loginService.selectNo(loginVo1);
	}

	@PostMapping("/test")
	public ResponseEntity<?> selectNo(@Validated LoginVo loginVo, @RequestBody LoginVo loginVo1, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}

		return loginService.selectNo(loginVo1);
	}

//    @GetMapping("/list")
//    public String signUp() {
//
//        return "index.html";
//    }

}