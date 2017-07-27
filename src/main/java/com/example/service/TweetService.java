package com.example.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.TweetDto;
import com.example.dto.UserDto;
import com.example.entities.Tweet;
import com.example.entities.User;
import com.example.mapper.ReplyTweetMapper;
import com.example.mapper.RepostTweetMapper;
import com.example.mapper.SimpleTweetMapper;
import com.example.mapper.TweetMapper;
import com.example.repository.TweetRepository;
import com.example.repository.UserRepository;
import com.example.repository.ReplyTweetRepository;
import com.example.repository.RepostTweetRepository;
import com.example.repository.SimpleTweetRepository;

@Service
public class TweetService {
	
	private SimpleTweetMapper simpleTweetMapper;
	private SimpleTweetRepository simpleTweetRepo;
	private RepostTweetMapper repostTweetMapper;
	private RepostTweetRepository repostTweetRepo;
	private ReplyTweetMapper replyTweetMapper;
	private ReplyTweetRepository replyTweetRepo;
	private TweetRepository tweetRepo;
	private TweetMapper tweetMapper;
	private UserRepository userRepo;

	public List<TweetDto> getTweets(String username) {
		if(userRepo.findByCredentialsUsernameAndIsAvailableTrue(username)!=null)
			return userRepo.findByCredentialsUsername(username).getTweets()				
				.stream().filter(Tweet::isNotDeleted).map(tweetMapper::toDto).sorted(Comparator.comparing(TweetDto::getPosted))
				.collect(Collectors.toList());
		else return null;
	}
	public List<TweetDto> getFeed(String username) {
		if(userRepo.findByCredentialsUsernameAndIsAvailableTrue(username)!=null){
		List<TweetDto>feed = getTweets(username);
		for (User user: userRepo.findByCredentialsUsernameAndIsAvailableTrue(username).getFollowed()) {
            for (TweetDto tweetDto: getTweets(user.getCredentials().getUsername())){
            	if(tweetMapper.fromDto(tweetDto).isNotDeleted())
            		feed.add(tweetDto);}}
		return feed.stream().sorted(Comparator.comparing(TweetDto::getPosted)).collect(Collectors.toList());}
		else return null;
	}

}
