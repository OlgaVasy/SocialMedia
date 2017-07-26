package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.dto.SimpleTweetDto;
import com.example.entities.SimpleTweet;

@Mapper(componentModel = "spring")
public interface SimpleTweetMapper {
	
	SimpleTweet fromDto(SimpleTweetDto dto);
	SimpleTweetDto toDto(SimpleTweet simpleTweet);

}
