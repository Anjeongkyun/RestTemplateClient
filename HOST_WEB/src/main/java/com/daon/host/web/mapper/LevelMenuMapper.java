package com.daon.host.web.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.daon.host.web.vo.LevelMenuVo;
import com.daon.host.web.vo.LevelMenuVo.CRUD;

@Component
@Mapper
public interface LevelMenuMapper {

	List<CRUD> getList(@Param("req") LevelMenuVo.CRUD levelMenu);
	int insertLevel(@Param("req") LevelMenuVo.CRUD levelMenu);
	int updateLevel(@Param("req")  LevelMenuVo.CRUD levelMenu);
	int deleteLevel(@Param("req") LevelMenuVo.CRUD levelMenu);
	

}
