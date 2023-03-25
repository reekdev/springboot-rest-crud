package com.springboot.restapp.mapper;

import com.springboot.restapp.dto.UserDTO;
import com.springboot.restapp.entity.User;

/*
 * This class will be responsible for mapping User entities to UserDTO and
 * mapping UserDTO to User entities
*/
public class UserMapper {

	// this method will convert User entity to UserDTO
	public static UserDTO mapTUserDTO(User user) {

		UserDTO userDto = new UserDTO(
				user.getUserId(),
				user.getUserFirstName(),
				user.getUserLastName(),
				user.getUserEmail());
		return userDto;
	}

	// this method will convert UserDTO to User entity
	public static User mapToUser(UserDTO userDto) {
		User user = new User(
				userDto.getUserId(),
				userDto.getUserFirstName(),
				userDto.getUserLastName(),
				userDto.getUserEmail());
		return user;
	}
}
