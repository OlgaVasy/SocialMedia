package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.dto.ReplyTweetDto;
import com.example.entities.ReplyTweet;

@Mapper(componentModel = "spring")
public interface ReplyTweetMapper {
	
	ReplyTweet fromDto(ReplyTweetDto dto);
	ReplyTweetDto toDto(ReplyTweet replyTweet);

}
