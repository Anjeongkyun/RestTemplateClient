package com.daon.host.web.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.daon.host.web.vo.CmmnVo;
import com.daon.host.web.vo.CmmnVo.CmmnBase;
import com.daon.host.web.vo.LoginVo;

@Component
@Mapper
public interface CmmnMapper {
	List<CmmnVo.CmmnCode> getCmmnCode(@Param("req") CmmnVo.CmmnCode cmmnCode); //파라미터로 받은 id를 연결해서 sql 매핑 문장에 #{id}로 전달

	List<CmmnVo.CmmnBase> getCmmnBase(@Param("req") CmmnVo.CmmnBase cmmnBase);
	
	
	
	
}
