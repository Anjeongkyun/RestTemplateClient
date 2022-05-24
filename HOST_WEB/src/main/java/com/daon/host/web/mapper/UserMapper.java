package com.daon.host.web.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.daon.host.web.vo.UserVo;
import com.daon.host.web.vo.UserVo.Table;


@Component
@Mapper
public interface UserMapper {

	List<Table> getUser(@Param("req") UserVo.User user);

	int insertUserManage(@Param("req") UserVo.Table user);

	int updateUserManage(@Param("req") UserVo.Table user);

	int deleteUserManage(@Param("req") UserVo.Table user);

	
	int insertUserManage(@Param("req") UserVo.CRUD user);

	int updateUserManage(@Param("req") UserVo.CRUD user);

	int deleteUserManage(@Param("req") UserVo.CRUD user);

	List<Table> getIdCheck(@Param("req") UserVo.CRUD user);
	

}
