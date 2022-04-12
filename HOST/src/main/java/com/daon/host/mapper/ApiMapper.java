package com.daon.host.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.daon.host.vo.BasicInfo;
import com.daon.host.vo.BasicInfo.Complex;
import com.daon.host.vo.MobileUser;

@Component
@Mapper
public interface ApiMapper {
	MobileUser selectUser(@Param("loginId") String loginId);
	int existsByEmail(@Param("loginId") String loginId);
	int insertUser(MobileUser user);
	int insertUserRole(@Param("loginId") String loginId, String role);
	int updateUser(@Param("loginId") String loginId, @Param("prm") MobileUser user);
	int deleteUser(@Param("loginId") String loginId);
	int deleteUserRole(@Param("loginId") String loginId);
	
	//단지정보
	List<BasicInfo.Complex> selectComplex();
	//소속단지정보
	BasicInfo.MyComplex selectMyComplex(@Param("aptId") String apt_id);
	//단지 - 동호
	BasicInfo.Dongho selectDongho(@Param("aptId") String apt_id);
	//단지 - 세대주 조회
	String selectHouseHold(@Param("aptId") String apt_id,@Param("dong") String dong, @Param("ho") String ho);
	//패스워드변경
	int updatePwd(@Param("prm") BasicInfo.UserPassword userPwd);
	//사용자차량 조회
	List<BasicInfo.UserCar> selectUserCar(@Param("aptId") String apt_id,@Param("dong") String dong, @Param("ho") String ho);
	//사용자차량 중복체크
	int existsByUserCar(@Param("prm") BasicInfo.UserCar userCar);
	//사용자차량 등록
	int insertUserCar(@Param("prm") BasicInfo.UserCar userCar);
	//공지사항 조회
	List<BasicInfo.Notice> selectNotice(@Param("aptId") String apt_id);
	//출입증 조회
	List<BasicInfo.ComplexPass> selectPass(@Param("passMoblie") String passMoblie);
	//출입증 등록
	List<BasicInfo.ComplexPass> insertPass(BasicInfo.ComplexPass cmpxPass);
	
	
}