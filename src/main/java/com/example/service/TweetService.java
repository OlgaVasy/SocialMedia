package com.example.service;

import com.example.mapper.ReplyTweetMapper;
import com.example.mapper.RepostTweetMapper;
import com.example.mapper.SimpleTweetMapper;
import com.example.repository.ReplyTweetRepository;
import com.example.repository.RepostTweetRepository;
import com.example.repository.SimpleTweetRepository;

public class TweetService {
	
	private SimpleTweetMapper simpleTweetMapper;
	private SimpleTweetRepository simpleTweetRepo;
	private RepostTweetMapper repostTweetMapper;
	private RepostTweetRepository repostTweetRepo;
	private ReplyTweetMapper replyTweetMapper;
	private ReplyTweetRepository replyTweetRepo;

/*	public TweetService(SimpleTweetMapper simpleTweetMapper, SimpleTweetRepository simpleTweetRepo,
			RepostTweetMapper repostTweetMapper, RepostTweetRepository repostTweetRepo,
			ReplyTweetMapper replyTweetMapper, ReplyTweetRepository replyTweetRepo) {
		this.simpleTweetMapper = simpleTweetMapper;
		this.simpleTweetRepo = simpleTweetRepo;
		this.repostTweetMapper = repostTweetMapper;
		this.repostTweetRepo = repostTweetRepo;
		this.replyTweetMapper = replyTweetMapper;
		this.replyTweetRepo = replyTweetRepo;*/
//	}

}
