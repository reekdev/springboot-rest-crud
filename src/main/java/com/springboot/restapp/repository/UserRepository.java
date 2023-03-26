package com.springboot.restapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserEmail(String userEmail);
}
