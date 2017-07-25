package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.dto.ProjectDto;
import com.example.dto.UserDto;
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
	public boolean create(UserDto buildIt) {
		User realUser = userMapper.fromDto(buildIt);
		userRepo.save(realUser);
		return true;
	}
	public UserDto findById(Long id) {
		return userMapper.toDto(userRepo.getOne(id));
	}
	public List<UserDto> getAll() {
		return userRepo.getAll().stream().map(userMapper::toDto).collect(Collectors.toList());
	}	

}
