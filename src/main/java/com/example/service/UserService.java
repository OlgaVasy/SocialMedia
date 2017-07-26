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
		if(usernameExists(realUser.getCredentials().getUsername()) && realUser.isAvailable()==false){
			realUser.setAvailable(true);
			return userMapper.toDto(realUser);
		}
		else if(findByUsername(realUser.getCredentials().getUsername())!=null)
			return null;
		else if(realUser.getCredentials().getUsername()==null || realUser.getCredentials().getPassword()==null || realUser.getProfile().getEmail()==null)
			return null;
		else{
			userRepo.save(realUser);
			realUser.setAvailable(true);
			return userMapper.toDto(realUser);
	}}
	public UserDto findById(Integer id) {
		return userMapper.toDto(userRepo.getOne(id));
	}
	public List<UserDto> getAll() {
		return userRepo.findByIsAvailableTrue().stream().map(userMapper::toDto).collect(Collectors.toList());
	}	
	public UserDto findByUsername(String username) {
		return userMapper.toDto(userRepo.findByCredentialsUsername(username));
	}
	public boolean usernameExists(String username){
		return userRepo.findByCredentialsUsername(username)!=null;
	}
	public boolean usernameAvailable(String username){	
		return userRepo.findByCredentialsUsernameAndIsAvailableTrue(username)!=null;
	}
	public UserDto delete(Credentials credentials){
		if(usernameAvailable(credentials.getUsername())==false)
			return null;
		else if(credentials.getPassword()!=userRepo.findByCredentialsUsername(credentials.getUsername()).getCredentials().getPassword())
			return null;
		else {
			userRepo.findByCredentialsUsername(credentials.getUsername()).setAvailable(false);
			return userMapper.toDto(userRepo.findByCredentialsUsername(credentials.getUsername()));
		}
	}
	public UserDto patch(UserDtoToCreate updateIt) {
		User newData = userMapper.fromDtoToCreate(updateIt);
		User realUser = userRepo.findByCredentialsUsername(updateIt.getCredentials().getUsername());
		if(usernameAvailable(newData.getCredentials().getUsername())==false){			
			return null;
		}
		else if(updateIt.getCredentials().getPassword()!=realUser.getCredentials().getPassword())
			return null;		
		else{
			realUser.setProfile(newData.getProfile());			
			return userMapper.toDto(realUser);
	}}

}
