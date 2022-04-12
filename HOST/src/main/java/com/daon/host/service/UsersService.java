package com.daon.host.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daon.host.controller.StompConnect;
import com.daon.host.dto.Response;
import com.daon.host.dto.UserRequestDto;
import com.daon.host.dto.UserResponseDto;
import com.daon.host.entity.Authority;
import com.daon.host.entity.UserTerms;
import com.daon.host.entity.Users;
import com.daon.host.jwt.JwtTokenProvider;
import com.daon.host.repository.UserTermsRepository;
import com.daon.host.repository.UsersRepository;
import com.daon.host.utils.SecurityUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:websocketInfo.properties")
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserTermsRepository userTermsRepository;
    private final Response response;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate redisTemplate;
    @Autowired
    private ApiService apiService;
    @Autowired
	private StompConnect conn;
	@Value("${websocket.url}")
	private String url;
	@Autowired
    private Validator validator;
    
    //회원가입
    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp) {
    	//validation check
    	//request data - json으로 넘어올때 사용
//    	Set<ConstraintViolation<UserRequestDto.SignUp>> valiUser = validator.validate(signUp);
//        if (!valiUser.isEmpty()) {
//            StringBuilder sb = new StringBuilder();
//            for (ConstraintViolation<UserRequestDto.SignUp> constraintViolation : valiUser) {
//                sb.append(constraintViolation.getMessage());
//            }
//            return response.fail(sb.toString(), HttpStatus.BAD_REQUEST);
//        }
        
    	if (usersRepository.existsByEmail(signUp.getEmail())) {
            return response.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
        
        Users user = Users.builder()
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .role(Collections.singletonList(Authority.ROLE_USER.name()))
                .apt_id(signUp.getApt_id())
                .member_nm(signUp.getMember_nm())
                .member_mobile(signUp.getMember_mobile())
                .blue_id(signUp.getBlue_id())
                .dong(signUp.getDong())
                .ho(signUp.getHo())
                .household_yn(signUp.getHousehold_yn())
                .car_no(signUp.getCar_no())
                .car_type(signUp.getCar_type())
                .ins_id(signUp.getEmail())
                .upd_id(signUp.getEmail())
                .build();
        usersRepository.save(user);
        
        //이용약관 5개 저장. 화면에서 약관 전체Y처리일때 일괄저장.
        //필수,선택 약관이 나뉘면 재구성
//        String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMDD"));
//        UserTerms userTerms1 = new UserTerms("t01",signUp.getEmail(),"Y",localDate.toString(),signUp.getEmail(),signUp.getEmail());
//        UserTerms userTerms2 = new UserTerms("t02",signUp.getEmail(),"Y",localDate.toString(),signUp.getEmail(),signUp.getEmail());
//        UserTerms userTerms3 = new UserTerms("t03",signUp.getEmail(),"Y",localDate.toString(),signUp.getEmail(),signUp.getEmail());
//        UserTerms userTerms4 = new UserTerms("t04",signUp.getEmail(),"Y",localDate.toString(),signUp.getEmail(),signUp.getEmail());
//        UserTerms userTerms5 = new UserTerms("t05",signUp.getEmail(),"Y",localDate.toString(),signUp.getEmail(),signUp.getEmail());
//        
//        List<UserTerms> utList = Arrays.asList(userTerms1,userTerms2,userTerms3,userTerms4,userTerms5);
//        
//        userTermsRepository.saveAll(utList);

        return response.success("회원가입에 성공했습니다.");
    }
    
    //로그인
    public ResponseEntity<?> login(UserRequestDto.Login login){

        if (usersRepository.findByEmail(login.getEmail()).orElse(null) == null) {
            return response.fail("해당하는 유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        Authentication authentication = null; 
        try { 
        	authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken); 
        } catch (BadCredentialsException e) { 
//        	throw new BadCredentialsException("[" + login.getEmail() + "] 사용자 정보가 존재 하지 않습니다."); 
        	return response.fail("아이디 또는 비밀번호가 일치하지 않습니다. 다시 확인하신 후 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
        redisTemplate.opsForValue()
        .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);
        // 4-1. AccessToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
        redisTemplate.opsForValue()
        .set("AT:" + authentication.getName(), tokenInfo.getAccessToken(), tokenInfo.getAccessTokenExpirationTime(), TimeUnit.MILLISECONDS);
        
        //5. 사용자 고유키 발급
        UserRequestDto.UserKey userInfo = new UserRequestDto.UserKey();
        userInfo.setLoginId(login.getEmail());
        userInfo.setAccessToken(tokenInfo.getAccessToken());
        userInfo.setRefreshToken(tokenInfo.getRefreshToken());
        String userKey = apiService.setUserKey(userInfo);
        
        //6. 웹소켓 접속 및 구독(사용자 고유키)
        //6-1. ws connect
        conn.websocketConnect(url,userKey);
        
//        conn.sendMessage("ALD01", "이거 왜안가니");
        
        return response.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK);
    }
    
    //인증정보 갱신
    public ResponseEntity<?> reissue(UserRequestDto.Reissue reissue) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());

        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // (추가) 로그아웃되어 Redis 에 RefreshToken 이 존재하지 않는 경우 처리
        if(ObjectUtils.isEmpty(refreshToken)) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
        if(!refreshToken.equals(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 4. 새로운 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 5. RefreshToken Redis 업데이트
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "Token 정보가 갱신되었습니다.", HttpStatus.OK);
    }
    
    //로그아웃
    public ResponseEntity<?> logout(UserRequestDto.Logout logout) {
        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(logout.getAccessToken())) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(logout.getAccessToken());

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
//        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());
//        redisTemplate.opsForValue()
//                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return response.success("로그아웃 되었습니다.");
    }
    
    //유저권한 저장
    public ResponseEntity<?> authority() {
        // SecurityContext에 담겨 있는 authentication userEamil 정보
        String userEmail = SecurityUtil.getCurrentUserEmail();

        Users user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No authentication information."));

        // add ROLE_ADMIN
        user.getRole().add(Authority.ROLE_ADMIN.name());
        usersRepository.save(user);

        return response.success();
    }
    
    //로컬서버 데이터 요청.
    //param : loginId, aptId, gbn
    //gbn : C-내차위지/H-세대주이름
    public ResponseEntity<?> selectLocalInfo(String gbn, String loginId, String aptId) {
    	if(ObjectUtils.isEmpty(loginId) ) {
    		return response.fail("조회 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
    	}
    	
    	String sendMsg = gbn+","+aptId+","+loginId+",";
    	
    	boolean rtnYn = conn.sendMessage(aptId, sendMsg);
    	if(!rtnYn) {
    		return response.fail("데이터 요청 실패. 관리자에게 문의 해 주세요.", HttpStatus.BAD_REQUEST);
    	}
    	
    	try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//ws에서 받은 데이터를 redis에서 꺼내온다.
    	String rtnVal = (String)redisTemplate.opsForValue().get("RV:" + loginId);
    	if(ObjectUtils.isEmpty(rtnVal)) {
    		return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
    	}
    	//redis에서 꺼내오고 삭제한다.
    	redisTemplate.delete("RV:" + loginId);
    	
    	return response.success(rtnVal, "조회 성공했습니다.", HttpStatus.OK);
    }
}