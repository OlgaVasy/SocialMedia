package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.dto.TweetDto;
import com.example.entities.Tweet;

@Mapper(componentModel = "spring")
public interface TweetMapper {

	Tweet fromDto(TweetDto dto);
	TweetDto toDto(Tweet tweet);
	


}
