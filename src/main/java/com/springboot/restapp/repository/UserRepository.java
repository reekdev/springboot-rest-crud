package com.springboot.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
