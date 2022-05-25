package com.daon.host.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.daon.host.web.vo.ParkVo;

@Component
@Mapper
public interface ParkMapper {
	
	List<ParkVo> getList();	
}
