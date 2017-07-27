package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.dto.ReplyTweetDto;
import com.example.dto.RepostTweetDto;
import com.example.dto.SimpleTweetDto;
import com.example.dto.TweetDto;
import com.example.entities.Tweet;

@Mapper(componentModel = "spring")
public interface TweetMapper {

	Tweet fromSimpleDto(SimpleTweetDto simpleDto);
	SimpleTweetDto toSimpleDto(Tweet tweet);
	
	Tweet fromReplyDto(ReplyTweetDto replyDto);
	ReplyTweetDto toReplyDto(Tweet tweet);
	
	Tweet fromRepostDto(RepostTweetDto repostDto);
	RepostTweetDto toRepostDto(Tweet tweet);
	
	Tweet fromDto(TweetDto dto);
	TweetDto toDto(Tweet tweet);
	


}
