package com.example.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.SimpleTweetDto;
import com.example.dto.TweetDto;
import com.example.entities.Credentials;
import com.example.entities.SimpleTweet;
import com.example.entities.Tweet;
import com.example.entities.User;
import com.example.mapper.ReplyTweetMapper;
import com.example.mapper.RepostTweetMapper;
import com.example.mapper.SimpleTweetMapper;
import com.example.mapper.TweetMapper;
import com.example.repository.TweetRepository;
import com.example.repository.UserRepository;
import com.google.common.collect.Lists;
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
		if (userRepo.findByCredentialsUsernameAndIsAvailableTrue(username) != null)
			return Lists.reverse(userRepo.findByCredentialsUsername(username).getTweets().stream()
					.filter(Tweet::isNotDeleted).map(tweetMapper::toDto)
					.sorted(Comparator.comparing(TweetDto::getPosted)).collect(Collectors.toList()));
		else
			return null;
	}

	public List<TweetDto> getFeed(String username) {
		if (userRepo.findByCredentialsUsernameAndIsAvailableTrue(username) != null) {
			List<TweetDto> feed = getTweets(username);
			for (User user : userRepo.findByCredentialsUsernameAndIsAvailableTrue(username).getFollowed()) {
				for (TweetDto tweetDto : getTweets(user.getCredentials().getUsername())) {
					if (tweetMapper.fromDto(tweetDto).isNotDeleted())
						feed.add(tweetDto);
				}
			}
			return Lists.reverse(
					feed.stream().sorted(Comparator.comparing(TweetDto::getPosted)).collect(Collectors.toList()));
		} else
			return null;
	}

	public List<TweetDto> getAll() {
		return tweetRepo.findByIsNotDeletedTrue().stream().map(tweetMapper::toDto).collect(Collectors.toList());
	}

	public List<SimpleTweetDto> getAllSimple() {
		return simpleTweetRepo.findByTweetIsNotDeletedTrue().stream().map(simpleTweetMapper::toDto)
				.collect(Collectors.toList());
	}

	public List<SimpleTweetDto> taggedTweet(String label) {
		List<SimpleTweetDto> list = new ArrayList<SimpleTweetDto>();
		boolean tagged = false;
		for (SimpleTweetDto simpleTweetDto : getAllSimple()) {
			if (String.valueOf(simpleTweetDto.getContent().charAt(0)) == "#") {
				for (int i = 1; i < label.toCharArray().length; i++) {
					if (label.toCharArray()[i] == simpleTweetDto.getContent().toCharArray()[i])
						tagged = true;
					else {
						tagged = false;
						break;
					}
				}
				if (tagged == true)
					list.add(simpleTweetDto);
			}
			tagged = false;
		}
		if (list.size() > 0)
			return list;
		else
			return null;
	}

	public TweetDto findById(Integer id) {
		return tweetMapper.toDto(tweetRepo.getOne(id));
	}

	public TweetDto delete(Credentials credentials, Integer id) {
		if (findById(id) == null)
			return null;
		else if (credentials.getPassword() != userRepo.findByCredentialsUsername(credentials.getUsername())
				.getCredentials().getPassword())
			return null;
		else {
			tweetMapper.fromDto(findById(id)).setNotDeleted(false);
			tweetRepo.save(tweetMapper.fromDto(findById(id)));
			return findById(id);
		}
	}

	public SimpleTweetDto post(Credentials credentials, String content) {
		if (userRepo.findByCredentialsUsernameAndIsAvailableTrue(credentials.getUsername()) == null)
			return null;
		else if (credentials.getPassword() != userRepo.findByCredentialsUsername(credentials.getUsername())
				.getCredentials().getPassword())
			return null;
		else {
			SimpleTweet simpleTweet = null;
			simpleTweet.getTweet()
					.setAuthor(userRepo.findByCredentialsUsernameAndIsAvailableTrue(credentials.getUsername()));
			simpleTweet.getTweet().getAuthor().setCredentials(credentials);
			userRepo.save(simpleTweet.getTweet().getAuthor());
			simpleTweet.setContent(content);
			simpleTweetRepo.save(simpleTweet);
			tweetRepo.save(simpleTweet.getTweet());
			userRepo.findByCredentialsUsernameAndIsAvailableTrue(credentials.getUsername()).getTweets()
					.add(simpleTweet.getTweet());

			if (String.valueOf(simpleTweet.getContent().charAt(0)) == "#") {
				String label = simpleTweet.getContent().split(" ")[0];
			}

			// TODO add hashtag

			return simpleTweetMapper.toDto(simpleTweet);

		}
	}

	public boolean like(Credentials credentials, Integer id) {
		if (userRepo.findByCredentialsUsernameAndIsAvailableTrue(credentials.getUsername()) == null)
			return false;
		else if (credentials.getPassword() != userRepo.findByCredentialsUsername(credentials.getUsername())
				.getCredentials().getPassword())
			return false;
		else {
			tweetRepo.findOne(id).getLiked().add(userRepo.findByCredentialsUsernameAndIsAvailableTrue(credentials.getUsername()));
			return true;
		}
	}
	public List<User> likes(Integer id){
		
		
		return null;
		
	}
}
