package com.daon.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daon.host.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);
}
