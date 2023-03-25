package com.springboot.restapp.service;

import java.util.List;

import com.springboot.restapp.dto.UserDTO;
import com.springboot.restapp.entity.User;

public interface UserService {
	
	UserDTO createUser(UserDTO user);
	
	UserDTO getUserById(Long userId);
	
	List<UserDTO> getAllUsers();
	
	UserDTO updateUser(UserDTO user);
	
	void deleteUser(Long userId);
}
