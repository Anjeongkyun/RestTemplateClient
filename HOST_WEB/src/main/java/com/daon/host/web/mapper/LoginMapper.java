package com.daon.host.web.mapper;

import java.util.List;

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

import com.daon.host.web.vo.LoginVo;

@Component
@Mapper
public interface LoginMapper {
	
	//@Select("select * from tb_o_webuser where login_id=${loginVo.loginId}")
	//@Options(useGeneratedKeys = true, keyProperty = "login_id")  // 이런식으로 insert 후 자동 증가 값 까지 조회
	LoginVo.Table getLogin(@Param("login") LoginVo.Login login); //파라미터로 받은 id를 연결해서 sql 매핑 문장에 #{id}로 전달
	
	
}
