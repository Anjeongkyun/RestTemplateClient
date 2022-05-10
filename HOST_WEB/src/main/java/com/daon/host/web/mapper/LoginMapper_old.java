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
public interface LoginMapper_old {
	
	@Select("select * from tb_o_webuser where login_id=${loginVo.loginId}")
	@Options(useGeneratedKeys = true, keyProperty = "login_id")  // 이런식으로 insert 후 자동 증가 값 까지 조회
	LoginVo getLogin(@Param("loginVo") LoginVo loginVo); //파라미터로 받은 id를 연결해서 sql 매핑 문장에 #{id}로 전달
	
	//@Select("select * from tb_o_webuser")
	List<LoginVo> getLoginAll(@Param("loginVo") LoginVo loginVo); //파라미터로 받은 id를 연결해서 sql 매핑 문장에 #{id}로 전달
	
	@Select("select * from tb_o_webuser")
	@Results({
		@Result(property="loginId", column="login_id"),
		
	})
	List<LoginVo> getLoginList();

	@Insert("insert into tb_o_webuser ("
			+ "login_id"
			+ "	,login_pw"
			+ "	,member_nm"
			+ "	,member_role"
			+ "	,member_phone"
			+ "	,use_yn"
			+ "	,ins_id"
			//+ "	ins_date"
			+ "	,upd_id"
			//+ "	upd_date"
			+ ")values ("
			+ "#{loginVo.loginId},"
			+ "#{loginVo.loginPw},"
			+ "#{loginVo.memberMm},"
			+ "#{loginVo.memberRole},"
			+ "#{loginVo.memberPhone},"
			+ "#{loginVo.useYn},"
			+ "#{loginVo.insId},"
			+ "#{loginVo.updId})")
	int putLogin1(@Param("loginVo") LoginVo loginVo);
	
	
	@Insert("insert into tb_o_webuser values (${loginId},${loginPw},${memberMm},${memberRole},${}memberPhone,${useYn},${insId},${insDate})")
	int putLogin(@PathVariable("loginId") String loginId, @RequestParam("loginPw") String loginPw,
			@RequestParam("memberMm") String memberMm, @RequestParam("memberRole") String memberRole,
			@RequestParam("memberPhone") String memberPhone, @RequestParam("useYn") String useYn,
			@RequestParam("insId") String insId, @RequestParam("insDate") String insDate);
	
	@Update("update tb_o_webuser set loginId= ${loginId},loginPw=${loginPw},memberMm=${memberMm},"
			+ "memberRole= ${memberRole},memberPhone=${memberPhone},useYn= ${useYn},insId=${insId},insDate=${insDate} where login_id = ${loginId)")
	int postLogin(@PathVariable("loginId") String loginId, @RequestParam("loginPw") String loginPw,
			@RequestParam("memberMm") String memberMm, @RequestParam("memberRole") String memberRole,
			@RequestParam("memberPhone") String memberPhone, @RequestParam("useYn") String useYn,
			@RequestParam("updId") String updId, @RequestParam("updDate") String updDate);

	@Delete("delete from tb_o_webuser whre id=${loginId}")
	int deleteLogin(@PathVariable("loginId") String loginId);
}
