package com.daon.host.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.daon.host.web.vo.Notice;

@Component
@Mapper
public interface NoticeMapper {
	List<Notice> getList();
	Notice getDetail(@Param("noticeNo") String noticeNo);	
	void insert(Notice notice);
	void delete(@Param("noticeNo") String noticeNo);
	void update(Notice notice);
	
}
