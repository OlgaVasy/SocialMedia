package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.dto.UserDto;
import com.example.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {	

	User fromDto(UserDto dto);
	UserDto toDto(User user);

}
