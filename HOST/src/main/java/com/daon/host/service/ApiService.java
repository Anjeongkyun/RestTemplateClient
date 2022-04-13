package com.daon.host.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.controller.StompConnect;
import com.daon.host.dto.Response;
import com.daon.host.dto.UserRequestDto;
import com.daon.host.jwt.JwtTokenProvider;
import com.daon.host.mapper.ApiMapper;
import com.daon.host.vo.BasicInfo;
import com.daon.host.vo.MobileUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ApiService {
	
	@Autowired
	private ApiMapper apiMapper;
	
	@Autowired
    private Validator validator;
	
	@Autowired
	private StompConnect conn;
	
    private final Response response;
    private final JwtTokenProvider jwtTokenProvider;
//    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate redisTemplate;
    private final PasswordEncoder passwordEncoder;
    
    //Select
    public ResponseEntity<?> selectUser(String loginId) {
    	if(ObjectUtils.isEmpty(loginId)) {
            return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
        }
    	
    	MobileUser muser = apiMapper.selectUser(loginId); 
    	
    	if(ObjectUtils.isEmpty(muser)) {
    		return response.fail("해당정보로 조회된 ID가 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(muser, "조회 성공했습니다.", HttpStatus.OK);
	}
    //Insert
    public ResponseEntity<?> saveUser(MobileUser user) { 
    	
    	//validation check
    	Set<ConstraintViolation<MobileUser>> valiUser = validator.validate(user);
        if (!valiUser.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<MobileUser> constraintViolation : valiUser) {
                sb.append(constraintViolation.getMessage());
            }
            return response.fail(sb.toString(), HttpStatus.BAD_REQUEST);
        }
    	
    	if (apiMapper.existsByEmail(user.getLogin_id()) > 0) {
            return response.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    
    	MobileUser muser = user;
    	muser.setLogin_pw(passwordEncoder.encode(user.getLogin_pw()));
    	muser.setIns_id(user.getLogin_id());
    	muser.setUpd_id(user.getLogin_id());
    	//사용자정보
    	apiMapper.insertUser(muser);
    	//사용자권한
    	apiMapper.insertUserRole(user.getLogin_id(),"ROLE_USER");
    	
    	return response.success("저장에 성공했습니다.", HttpStatus.CREATED);
    }
    //Update - put
    public ResponseEntity<?> updateUser(String loginId, MobileUser user) { 
    	//validation check
    	Set<ConstraintViolation<MobileUser>> valiUser = validator.validate(user);
        if (!valiUser.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<MobileUser> constraintViolation : valiUser) {
                sb.append(constraintViolation.getMessage());
            }
            return response.fail(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        
    	if (apiMapper.existsByEmail(loginId) == 0) {
    		return response.fail("해당정보로 조회된 ID가 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
    	apiMapper.updateUser(loginId, user);
    	
    	return response.success("데이터 수정에 성공했습니다.", HttpStatus.OK);
    }
    //Delete
    public ResponseEntity<?> deleteUser(String loginId) { 
    	if(ObjectUtils.isEmpty(loginId)) {
            return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
        }
    	
    	MobileUser muser = apiMapper.selectUser(loginId); 
    	
    	if(ObjectUtils.isEmpty(muser)) {
    		return response.fail("해당정보로 조회된 ID가 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
    	apiMapper.deleteUserRole(loginId);
    	apiMapper.deleteUser(loginId);
    	
    	return response.success("데이터 삭제에 성공했습니다.", HttpStatus.NO_CONTENT);
    }
    
    //비밀번호 변경
    public ResponseEntity<?> updatePwd(BasicInfo.UserPassword userpwd) {
    	if(ObjectUtils.isEmpty(userpwd.getLogin_id() ) ) {
    		return response.fail("로그인ID 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
    	
    	if (apiMapper.existsByEmail(userpwd.getLogin_id()) == 0) {
    		return response.fail("해당정보로 조회된 ID가 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
    	userpwd.setLogin_pw(passwordEncoder.encode(userpwd.getLogin_pw()) );
    	
    	apiMapper.updatePwd(userpwd);
    	
    	return response.success("패스워드 변경에 성공했습니다.", HttpStatus.OK);
    }
    
    //App사용자가 로그인 이후 웹소켓 구독시 사용하는 고유키 
    public String setUserKey(UserRequestDto.UserKey UserInfo) {
    	
    	//validation check
    	Set<ConstraintViolation<UserRequestDto.UserKey>> valiUserKey = validator.validate(UserInfo);
        if (!valiUserKey.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<UserRequestDto.UserKey> constraintViolation : valiUserKey) {
                sb.append(constraintViolation.getMessage());
            }
            return sb.toString();
        }
    	
    	//1. jwt accessToken 유효성체크
    	if (!jwtTokenProvider.validateToken(UserInfo.getRefreshToken())) {
            return "Refresh Token 정보가 유효하지 않습니다.";
        }
    	//2. 사용자 고유키 생성
    	String userKey = UserInfo.getLoginId();
    	//3. 사용자 고유키 redis set (expirationTime 설정을 통해 자동 삭제 처리)
    	redisTemplate.opsForValue()
    		.set("UK:" + userKey, userKey);
    	//4. 사용자 고유키 리턴
        return userKey;
    }
    
    //App사용자가 로그인 이후 웹소켓 구독시 사용하는 고유키 
    //1. param : login id > redis get key
    //2. 사용자 고유키 응답.
    public ResponseEntity<?> getUserKey(String loginId) {
    	
    	if(ObjectUtils.isEmpty(loginId)) {
            return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
        }
    	// 1. Redis에서  을 기반으로 저장된 userKey 값을 가져옵니다.
    	String userKey = (String)redisTemplate.opsForValue().get("UK:" + loginId);
    	if(ObjectUtils.isEmpty(userKey)) {
    		return response.fail("등록된 고유키 정보가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
    	
        return response.success(userKey, "사용자 고유키 조회 성공했습니다.", HttpStatus.OK);
    }
    
    //단지 정보 조회
    public ResponseEntity<?> selectComplex() {
    	
    	List<BasicInfo.Complex> cmpx = apiMapper.selectComplex(); 
    	
    	if(ObjectUtils.isEmpty(cmpx)) {
    		return response.fail("해당정보로 조회된 단지가 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(cmpx, "조회 성공했습니다.", HttpStatus.OK);
	}
    
    //소속단지 정보 조회
    public ResponseEntity<?> selectMyComplex(String aptId) {
    	if(ObjectUtils.isEmpty(aptId)) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
    	
    	BasicInfo.MyComplex cmpx = apiMapper.selectMyComplex(aptId); 
    	
    	if(ObjectUtils.isEmpty(cmpx)) {
    		return response.fail("해당정보로 조회된 단지가 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
    	return response.success(cmpx, "조회 성공했습니다.", HttpStatus.OK);
    }
    
    //단지 동호정보 조회
    public ResponseEntity<?> selectDongho(String aptId) {
    	if(ObjectUtils.isEmpty(aptId)) {
            return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
        }
    	
    	BasicInfo.Dongho dh = apiMapper.selectDongho(aptId); 
    	
    	if(ObjectUtils.isEmpty(dh)) {
    		return response.fail("해당정보로 조회된 동호정보가 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
		return response.success(dh, "조회 성공했습니다.", HttpStatus.OK);
	}
    
    //단지 세대주 조회
    //param : apt_id+dong+ho : 세대주이름
    public ResponseEntity<?> selectHouseHold(String aptId, String dong, String ho) {
    	if(ObjectUtils.isEmpty(aptId) || ObjectUtils.isEmpty(dong) || ObjectUtils.isEmpty(ho)) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
    	
    	String hhName = apiMapper.selectHouseHold(aptId,dong,ho); 
    	
    	if(ObjectUtils.isEmpty(hhName)) {
    		return response.fail("해당정보로 조회된 동호정보가 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
    	return response.success(hhName, "조회 성공했습니다.", HttpStatus.OK);
    }
    
    //사용자 차량조회
    //param : apt_id,dong,ho
    public ResponseEntity<?> selectUserCar(String aptId, String dong, String ho) {
    	if(ObjectUtils.isEmpty(aptId) || ObjectUtils.isEmpty(dong) || ObjectUtils.isEmpty(ho)) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
    	
    	List<BasicInfo.UserCar> car = apiMapper.selectUserCar(aptId,dong,ho); 
    	
    	if(ObjectUtils.isEmpty(car)) {
    		return response.fail("해당정보로 조회된 차량정보가 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
    	return response.success(car, "조회 성공했습니다.", HttpStatus.OK);
    }
    //사용자 차량등록
    public ResponseEntity<?> insertUserCar(BasicInfo.UserCar userCar) { 
    	
    	if (apiMapper.existsByUserCar(userCar) > 0) {
            return response.fail("등록된 차량번호가 존재합니다.", HttpStatus.BAD_REQUEST);
        }
    
    	//사용자 차량정보
    	apiMapper.insertUserCar(userCar);
    	
    	return response.success("저장에 성공했습니다.", HttpStatus.CREATED);
    }
    
    //공지사항 조회
    //param : apt_id | 단지코드:null-> 전체 / 단지코드:not null - > 해당단지
    public ResponseEntity<?> selectNotice(String aptId) {
    	
    	List<BasicInfo.Notice> noti = apiMapper.selectNotice(aptId); 
    	
    	if(ObjectUtils.isEmpty(noti)) {
    		return response.fail("해당정보로 조회된 공지사항이 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
    	return response.success(noti, "조회 성공했습니다.", HttpStatus.OK);
    }
    
    //출입증 조회
    //param : pass_mobile 
    public ResponseEntity<?> selectPass(String passMobile) {
    	
    	List<BasicInfo.ComplexPass> cmpxPass = apiMapper.selectPass(passMobile); 
    	
    	if(ObjectUtils.isEmpty(cmpxPass)) {
    		return response.fail("해당정보로 조회된 출입증목록이 없습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
    	return response.success(cmpxPass, "조회 성공했습니다.", HttpStatus.OK);
    }
    
    //출입증 등록
    //param : pass_mobile 
    public ResponseEntity<?> insertPass(BasicInfo.ComplexPass cmpxPass) { 
    	
    	
    	//출입증 등록 및 발급정보
    	apiMapper.insertPass(cmpxPass);
    	
    	return response.success("저장에 성공했습니다.", HttpStatus.CREATED);
    }
}
