package com.daon.host.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.daon.host.web.vo.NoticeVo;

@Component
@Mapper
public interface NoticeMapper {	
	void insert(NoticeVo notice);
	void update(NoticeVo notice);
	
}
