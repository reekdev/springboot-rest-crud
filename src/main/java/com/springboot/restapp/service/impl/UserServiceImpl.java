package com.springboot.restapp.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.restapp.entity.User;
import com.springboot.restapp.repository.UserRepository;
import com.springboot.restapp.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long userId) {
		Optional<User> possibleUser = userRepository.findById(userId);
		return possibleUser.get();
	}

}
