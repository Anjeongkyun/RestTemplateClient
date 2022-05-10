package com.daon.host.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.daon.host.web.vo.UserProfile;

@Mapper  // 인터페이스를 매퍼로 인식
public interface UserProfileMapper {
	
	@Select("select * from tb_o_webuser where =${id}")
	UserProfile getUserProfile(@Param("id") String id); //파라미터로 받은 id를 연결해서 sql 매핑 문장에 ${id}로 전달
	
	@Select("select * from tb_o_webuser")
	List<UserProfile> getUserProfileList();
	//정상이면 1이 반환됨
	int putUserProfile(String id, String name, String phone, String address);
	
	
	int postUserprofile(String id, String name, String phone, String address) ;
	
	
	int deleteUserprofile(String id) ;
}
