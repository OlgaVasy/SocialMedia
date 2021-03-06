package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.UserDto;
import com.example.dto.UserDtoToCreate;
import com.example.entities.Credentials;
import com.example.entities.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;

@Service
public class UserService {

	private UserMapper userMapper;
	private UserRepository userRepo;

	public UserService(UserRepository userRepo, UserMapper userMapper) {
		this.userMapper = userMapper;
		this.userRepo = userRepo;
	}

	public UserDto post(UserDtoToCreate buildIt) {
		User realUser = userMapper.fromDtoToCreate(buildIt);
		if (usernameExists(realUser.getCredentials().getUsername()) && realUser.isAvailable() == false) {
			realUser.setAvailable(true);
			return userMapper.toDto(realUser);
		} else if (findByUsername(realUser.getCredentials().getUsername()) != null)
			return null;
		else if (realUser.getCredentials().getUsername() == null || realUser.getCredentials().getPassword() == null
				|| realUser.getProfile().getEmail() == null)
			return null;
		else {
			realUser.setAvailable(true);
			userRepo.save(realUser);
			return userMapper.toDto(realUser);
		}
	}

	public UserDto findById(Integer id) {
		return userMapper.toDto(userRepo.getOne(id));
	}

	public List<UserDto> getAll() {
		return userRepo.findByIsAvailableTrue().stream().map(userMapper::toDto).collect(Collectors.toList());
	}

	public UserDto findByUsername(String username) {
		if (userMapper.toDto(userRepo.findByCredentialsUsernameAndIsAvailableTrue(username)) != null)
			return userMapper.toDto(userRepo.findByCredentialsUsernameAndIsAvailableTrue(username));
		else
			return null;
	}

	public boolean usernameExists(String username) {
		return userRepo.findByCredentialsUsername(username) != null;
	}

	public boolean usernameAvailable(String username) {
		return userRepo.findByCredentialsUsernameAndIsAvailableTrue(username) != null;
	}

	public UserDto delete(Credentials credentials) {

		if (usernameAvailable(credentials.getUsername()) == false)
			return null;
		// else
		// if(credentials.getPassword()!=userRepo.findByCredentialsUsername(credentials.getUsername()).getCredentials().getPassword())
		// return null;
		else {
			userRepo.findByCredentialsUsername(credentials.getUsername()).setAvailable(false);
			userRepo.save(userRepo.findByCredentialsUsername(credentials.getUsername()));
			return userMapper.toDto(userRepo.findByCredentialsUsername(credentials.getUsername()));
		}
	}

	public UserDto patch(UserDtoToCreate buildIt, String username) {
		User realUser = userRepo.findByCredentialsUsername(username);
		if (usernameAvailable(username) == false) {
			return null;
		}
		// else
		// if(updateIt.getCredentials().getPassword()!=realUser.getCredentials().getPassword())
		// return null;
		else {
			realUser.setCredentials(buildIt.getCredentials());
			realUser.setProfile(buildIt.getProfile());
			userRepo.save(realUser);
			return userMapper.toDto(realUser);
		}
	}

	public boolean follow(Credentials credentials, String username) {
		User potentialFollower = userRepo.findByCredentialsUsername(credentials.getUsername());
		User potentialFollowed = userRepo.findByCredentialsUsername(username);
		if (!potentialFollowed.isAvailable())
			return false;
		else if (!potentialFollower.isAvailable())
			return false;
		else if (potentialFollowed.getFollowers().contains(potentialFollower))
			return false;
		// else
		// if(potentialFollower.getCredentials().getPassword()!=credentials.getPassword())
		// return false;
		else {
			potentialFollower.getFollowed().add(potentialFollowed);
			userRepo.save(potentialFollower);
			return true;
		}
	}

	public boolean unfollow(Credentials credentials, String username) {
		User Follower = userRepo.findByCredentialsUsername(credentials.getUsername());
		User Followed = userRepo.findByCredentialsUsername(username);
		if (!Followed.isAvailable())
			return false;
		else if (!Follower.isAvailable())
			return false;
		if (Followed.getFollowers().contains(Follower))
			return false;
		// else if(Follower.getCredentials().getPassword()!=credentials.getPassword())
		// return false;
		else {
			Follower.getFollowed().remove(Followed);
			userRepo.save(Follower);
			return true;
		}
	}

	public List<UserDto> getFollowers(String username) {
		return userRepo.findByCredentialsUsernameAndIsAvailableTrue(username).getFollowers().stream()
				.filter(User::isAvailable).map(userMapper::toDto).collect(Collectors.toList());
	}

	public List<UserDto> getFollowed(String username) {
		return userRepo.findByCredentialsUsernameAndIsAvailableTrue(username).getFollowed().stream()
				.filter(User::isAvailable).map(userMapper::toDto).collect(Collectors.toList());
	}
}
