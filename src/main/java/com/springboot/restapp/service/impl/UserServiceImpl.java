package com.springboot.restapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restapp.dto.UserDTO;
import com.springboot.restapp.entity.User;
import com.springboot.restapp.exception.EmailAlreadyExistsException;
import com.springboot.restapp.exception.ResourceNotFoundException;
import com.springboot.restapp.mapper.AutoUserMapper;
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
		
		// User user = modelMapper.map(userDto, User.class);
		
		Optional<User> possibleUser = userRepository.findByUserEmail(userDto.getUserEmail());
		
		if (possibleUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		
		User user = AutoUserMapper.MAPPER.mapToUser(userDto);
		User savedUser = userRepository.save(user);
		
		// Convert User JPA entity into UserDTO
		// UserDTO savedUserDto = UserMapper.mapTUserDTO(savedUser);
		
		// UserDTO savedUserDto = modelMapper.map(savedUser, UserDTO.class);
		UserDTO savedUserDto = AutoUserMapper.MAPPER.mapToUserDTO(savedUser);
		return savedUserDto;
	}

	@Override
	public UserDTO getUserById(Long userId) {
		// Optional<User> possibleUser = userRepository.findById(userId);
		
		User possibleUser = userRepository
				.findById(userId)
				.orElseThrow(
						() -> new ResourceNotFoundException("User", "id", userId)
						);
		// User user = possibleUser.get();
		// return UserMapper.mapTUserDTO(user);
		// return modelMapper.map(user, UserDTO.class);
		return AutoUserMapper.MAPPER.mapToUserDTO(possibleUser);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> possibleUsers = userRepository.findAll();
		/*
		 * return possibleUsers.stream() .map(UserMapper::mapTUserDTO)
		 * .collect(Collectors.toList());
		 */
		
		
		/*
		 * return possibleUsers.stream() .map((users) -> modelMapper.map(users,
		 * UserDTO.class)) .collect(Collectors.toList());
		 */
		
		return possibleUsers.stream()
				.map((users) -> AutoUserMapper.MAPPER.mapToUserDTO(users))
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		User existingUser = userRepository.findById(user.getUserId())
				.orElseThrow(
						() -> new ResourceNotFoundException("User", "id", user.getUserId())
						);
		existingUser.setUserFirstName(user.getUserFirstName());
		existingUser.setUserLastName(user.getUserLastName());
		existingUser.setUserEmail(user.getUserEmail());
		User updatedUser = userRepository.save(existingUser);
		// return UserMapper.mapTUserDTO(updatedUser);
		// return modelMapper.map(updatedUser, UserDTO.class);
		return AutoUserMapper.MAPPER.mapToUserDTO(updatedUser);
	}

	@Override
	public void deleteUser(Long userId) {
		
		userRepository.findById(userId)
				.orElseThrow(
						() -> new ResourceNotFoundException("User", "id", userId)
						);
		userRepository.deleteById(userId);
	}

}
