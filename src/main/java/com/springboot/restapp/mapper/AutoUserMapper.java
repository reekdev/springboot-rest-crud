package com.springboot.restapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.springboot.restapp.dto.UserDTO;
import com.springboot.restapp.entity.User;

@Mapper
public interface AutoUserMapper {
	
	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
	
	UserDTO mapToUserDTO(User user);
	
	User mapToUser(UserDTO userDto);
}
