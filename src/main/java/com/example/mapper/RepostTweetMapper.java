package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.dto.RepostTweetDto;
import com.example.entities.RepostTweet;

@Mapper(componentModel = "spring")
public interface RepostTweetMapper {
	
	RepostTweet fromDto(RepostTweetDto dto);
	RepostTweetDto toDto(RepostTweet repostTweet);

}
