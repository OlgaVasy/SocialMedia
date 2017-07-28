package com.example.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.SimpleTweetDto;
import com.example.dto.TweetDto;
import com.example.dto.TweetDtoToCreate;
import com.example.entities.Credentials;
import com.example.entities.Tweet;
import com.example.entities.User;
import com.example.mapper.TweetMapper;
import com.example.repository.TweetRepository;
import com.example.repository.UserRepository;
import com.google.common.collect.Lists;

@Service
public class TweetService {

	private TweetRepository tweetRepo;
	private TweetMapper tweetMapper;
	private UserRepository userRepo;

	public TweetService(TweetRepository tweetRepo, TweetMapper tweetMapper) {
		this.tweetMapper = tweetMapper;
		this.tweetRepo = tweetRepo;
	}

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
		return tweetRepo.findByIsNotDeletedTrue().stream().map(tweetMapper::toSimpleDto).collect(Collectors.toList());
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

	public SimpleTweetDto post(TweetDtoToCreate buildIt) {
		if (userRepo.findByCredentialsUsernameAndIsAvailableTrue(buildIt.getCredentials().getUsername()) == null)
			return null;
		// else if (credentials.getPassword() !=
		// userRepo.findByCredentialsUsername(credentials.getUsername())
		// .getCredentials().getPassword())
		// return null;
		else {
			Tweet newTweet = tweetMapper.fromTweetDtoToCreate(buildIt);
			newTweet.setNotDeleted(true);
			newTweet.setAuthor(
					userRepo.findByCredentialsUsernameAndIsAvailableTrue(buildIt.getCredentials().getUsername()));
			tweetRepo.save(newTweet);
			userRepo.findByCredentialsUsernameAndIsAvailableTrue(buildIt.getCredentials().getUsername()).getTweets()
					.add(newTweet);

			if (String.valueOf(newTweet.getContent().charAt(0)) == "#") {
				String label = newTweet.getContent().split(" ")[0];
			}
			// add hashtag & mentions

			return tweetMapper.toSimpleDto(newTweet);

		}
	}

	public boolean like(Credentials credentials, Integer id) {
		if (userRepo.findByCredentialsUsernameAndIsAvailableTrue(credentials.getUsername()) == null)
			return false;
		else if (credentials.getPassword() != userRepo.findByCredentialsUsername(credentials.getUsername())
				.getCredentials().getPassword())
			return false;
		else {
			tweetRepo.findOne(id).getLiked()
					.add(userRepo.findByCredentialsUsernameAndIsAvailableTrue(credentials.getUsername()));
			return true;
		}
	}

	public List<User> likes(Integer id) {
		if (tweetRepo.findByIdAndIsNotDeletedTrue(id) != null) {
			List<User> list = new ArrayList<User>();
			for (User user : userRepo.findAll()) {
				if (user.isAvailable()) {
					if (like(user.getCredentials(), id))
						list.add(user);
				}
			}
			return list;
		} else
			return null;
	}
}
