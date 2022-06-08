package com.daon.host.web.repository;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daon.host.web.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, String> {
	Optional<Notice> findByNoticeNo(String noticeNo);
	
	@Transactional
	void deleteByNoticeNo(String noticeNo);
	
}