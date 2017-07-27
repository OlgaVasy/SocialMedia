package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.SimpleTweetDto;
import com.example.dto.TweetDto;
import com.example.dto.UserDtoToCreate;
import com.example.entities.Credentials;
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
		if (tweetService.getTweets(username) != null)
			return (TweetDto) tweetService.getTweets(username);
		else {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The username doesn't exist.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@GetMapping("users/@{username}/feed")
	public TweetDto getFeed(@PathVariable String username, HttpServletResponse response) {
		if (tweetService.getFeed(username) != null)
			return (TweetDto) tweetService.getFeed(username);
		else {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The username doesn't exist.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@GetMapping("tags/@{label}")
	public SimpleTweetDto getTaggedTweet(@PathVariable String label, HttpServletResponse response) {
		if (tweetService.taggedTweet(label) != null)
			return (SimpleTweetDto) tweetService.taggedTweet(label);
		else {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No hashtag with the given label exists.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@GetMapping("tweets")
	public List<TweetDto> getTweets() {
		return tweetService.getAll();
	}

	@GetMapping("tweets/@{id}")
	public TweetDto getById(@PathVariable Integer id, HttpServletResponse response) {
		if (tweetService.findById(id) != null)
			return tweetService.findById(id);
		else {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Such tweet doesn't exist.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@DeleteMapping("tweets/{id}")
	public TweetDto deleteTheTweet(@RequestBody Credentials deleteIt, Integer id, HttpServletResponse response) {
		if (tweetService.delete(deleteIt, id) != null)
			return tweetService.findById(id);
		else {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"The tweet doesn't exist or the password is wrong.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@PostMapping("tweets")
	public SimpleTweetDto buildATweet(@RequestBody Credentials credentials, String content,
			HttpServletResponse response) {
		if (tweetService.post(credentials, content) != null)
			return tweetService.post(credentials, content);
		else {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"The credentials don't match an active user.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	@PostMapping("tweets/{id}/like")
	public void likeTweet(@RequestBody Credentials credentials, Integer id,
			HttpServletResponse response) {
		if (tweetService.like(credentials, id))
			response.setStatus(HttpServletResponse.SC_OK);
		else {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"The credentials don't match an active user or the tweet doesn't exist.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
