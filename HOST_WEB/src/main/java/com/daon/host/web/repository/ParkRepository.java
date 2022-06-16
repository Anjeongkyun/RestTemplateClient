package com.daon.host.web.repository;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daon.host.web.entity.ParkEntity;

@Repository
public interface ParkRepository extends JpaRepository<ParkEntity, String> {
	Optional<ParkEntity> findByAptId(String aptId);

	@Transactional
	void deleteByAptId(String aptId);
	
	
}