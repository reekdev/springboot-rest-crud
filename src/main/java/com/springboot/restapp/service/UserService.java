package com.springboot.restapp.service;

import com.springboot.restapp.entity.User;

public interface UserService {
	
	User createUser(User user);
	
	User getUserById(Long userId);
}
