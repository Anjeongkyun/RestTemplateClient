package com.daon.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daon.host.entity.UserTerms;

public interface UserTermsRepository extends JpaRepository<UserTerms, String>{
	
}
