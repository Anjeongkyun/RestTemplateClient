package com.daon.host.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.daon.host.web.vo.NoticeVo;

@Component
@Mapper
public interface NoticeMapper {
	List<NoticeVo> getList();
	NoticeVo getDetail(@Param("noticeNo") String noticeNo);	
	void insert(NoticeVo notice);
	void delete(@Param("noticeNo") String noticeNo);
	void update(NoticeVo notice);
	
}
