package com.example.controller;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.springframework.http.HttpStatus;
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

	@GetMapping("users")
	public List<UserDto> getUsers() {
		return userService.getAll();
	}
	
	@GetMapping("users/@{username}")
	public UserDto getUserByUsername(@PathVariable String username) {
		//if(userService.findByUsername(username)!=null)
			return userService.findByUsername(username);		
	}
	@GetMapping("validate/tag/exists/{label}")
	public boolean hashtagExists() {
		return ;
	}
	@GetMapping("validate/username/exists/@{username}")
	public boolean usernameExists() {
		return ;
	}
	@GetMapping("validate/username/available/@{username}")
	public boolean usernameAvailable() {
		return ;
	}
		
	@PostMapping
	public void buildAnUser(@RequestBody UserDto buildIt, HttpServletResponse response) {
		
		if(userService.create(buildIt))
			response.setStatus(HttpServletResponse.SC_CREATED);
		else
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}	

}