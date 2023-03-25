package com.springboot.restapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapp.dto.UserDTO;
import com.springboot.restapp.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// REST API: create a user
	@PostMapping()
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
		UserDTO newUser = userService.createUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	// REST API: get a user by their ID
	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) {
		UserDTO possibleUser = userService.getUserById(userId);
		return new ResponseEntity<>(possibleUser, HttpStatus.OK);
	}
	
	// REST API: get all users
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}
	
	// REST API: update a user
	@PutMapping("{id}")
	public ResponseEntity<UserDTO> updateUser(
			@PathVariable("id") Long userId,
			@RequestBody UserDTO user) {
		user.setUserId(userId);
		UserDTO updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	// REST API: delete a user - no need to use UserDTO here
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("user is successfully deleted.", HttpStatus.OK);
	}
	
}
