package com.daon.host.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.daon.host.vo.MobileUser;

@Component
@Mapper
public interface UserMapper {

	MobileUser findUserByLoginId(@Param("login_id") String login_id);
    int saveUser(@Param("param") MobileUser param);

}
