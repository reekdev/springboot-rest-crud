package com.springboot.restapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restapp.dto.UserDTO;
import com.springboot.restapp.entity.User;
import com.springboot.restapp.mapper.UserMapper;
import com.springboot.restapp.repository.UserRepository;
import com.springboot.restapp.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userDto) {
		
		// convert UserDTO into User JPA entity
		// User user = UserMapper.mapToUser(userDto);
		User user = modelMapper.map(userDto, User.class);
		User savedUser = userRepository.save(user);
		
		// Convert User JPA entity into UserDTO
		// UserDTO savedUserDto = UserMapper.mapTUserDTO(savedUser);
		UserDTO savedUserDto = modelMapper.map(savedUser, UserDTO.class);
		return savedUserDto;
	}

	@Override
	public UserDTO getUserById(Long userId) {
		Optional<User> possibleUser = userRepository.findById(userId);
		User user = possibleUser.get();
		// return UserMapper.mapTUserDTO(user);
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> possibleUsers = userRepository.findAll();
		/*
		 * return possibleUsers.stream() .map(UserMapper::mapTUserDTO)
		 * .collect(Collectors.toList());
		 */
		return possibleUsers.stream()
				.map((users) -> modelMapper.map(users, UserDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		User existingUser = userRepository.findById(user.getUserId()).get();
		existingUser.setUserFirstName(user.getUserFirstName());
		existingUser.setUserLastName(user.getUserLastName());
		existingUser.setUserEmail(user.getUserEmail());
		User updatedUser = userRepository.save(existingUser);
		// return UserMapper.mapTUserDTO(updatedUser);
		return modelMapper.map(updatedUser, UserDTO.class);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

}
