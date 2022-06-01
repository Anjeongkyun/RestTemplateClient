package com.daon.host.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.daon.host.web.vo.Notice;
import com.daon.host.web.vo.ParkVo;

@Component
@Mapper
public interface ParkMapper {
	
	List<ParkVo> getList();	
	ParkVo getDetail(@Param("aptId") String aptId);
	void insert(ParkVo parkVo);
	void update(ParkVo parkVo);
	void delete(@Param("aptId") String noticeNo);
}
