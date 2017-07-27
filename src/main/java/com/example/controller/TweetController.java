package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TweetDto;
import com.example.dto.UserDto;
import com.example.service.TweetService;

@RestController
@RequestMapping("tweet")
public class TweetController {
	
	private TweetService tweetService;

	public TweetController(TweetService tweetService) {
		this.tweetService = tweetService;
	}	
	@GetMapping("users/@{username}/tweets")
	public TweetDto getTweets(@PathVariable String username, HttpServletResponse response) {
		if(tweetService.getTweets(username)!=null)
			return (TweetDto) tweetService.getTweets(username);
		else {try {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"The username doesn't exist.");
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return null;
		}
	}	
	@GetMapping("users/@{username}/feed")
	public TweetDto getFeed(@PathVariable String username, HttpServletResponse response) {
		if(tweetService.getFeed(username)!=null)
			return (TweetDto) tweetService.getFeed(username);
		else {try {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"The username doesn't exist.");
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return null;
		}
	}	

}
