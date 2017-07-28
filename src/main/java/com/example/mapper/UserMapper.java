package com.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.dto.UserDto;
import com.example.dto.UserDtoToCreate;
import com.example.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mappings(@Mapping(source = "username", target = "credentials.username"))
	User fromDto(UserDto dto);

	@Mappings(@Mapping(source = "credentials.username", target = "username"))
	UserDto toDto(User user);

	User fromDtoToCreate(UserDtoToCreate dto);

	UserDtoToCreate toDtoToCreate(User user);

}
