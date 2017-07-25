package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDto;
import com.example.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}	

	@GetMapping
	public List<UserDto> users() {
		return userService.getAll();
	}
	
	@GetMapping("{id}")
	public UserDto getUserById(@PathVariable Long id) {
		return userService.findById(id);
	}
		
	@PostMapping
	public void buildAnUser(@RequestBody UserDto buildIt, HttpServletResponse response) {
		
		if(userService.create(buildIt))
			response.setStatus(HttpServletResponse.SC_CREATED);
		else
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}	

}