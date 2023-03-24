package com.springboot.restapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapp.entity.User;
import com.springboot.restapp.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
	
	private UserService userService;
	
	// REST API: create a user
	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User newUser = userService.createUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
}
