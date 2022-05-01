package com.daon.host.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.daon.host.web.vo.Notice;

@Component
@Mapper
public interface NoticeMapper {
	Notice selectNo(@Param("noticeNo") String noticeNo);
}
