package com.example.controller;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDto;
import com.example.dto.UserDtoToCreate;
import com.example.entities.Credentials;
import com.example.entities.Profile;
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
	public UserDto getUserByUsername(@PathVariable String username, HttpServletResponse response) {
		if(userService.findByUsername(username)!=null)
			return userService.findByUsername(username);
		else {try {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Such user doesn't exist or is deleted.");
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return null;
		}
	}	
	@GetMapping("validate/username/exists/@{username}")
	public boolean username(@PathVariable String username) {
		return userService.usernameExists(username);
	}
	@GetMapping("validate/username/available/@{username}")
	public boolean usernameAvailable(@PathVariable String username) {
		return userService.usernameAvailable(username);
	}		
	@PostMapping("users")
	public void buildAnUser(@RequestBody UserDtoToCreate buildIt, HttpServletResponse response) {		
		if(userService.post(buildIt)!=null)
			response.setStatus(HttpServletResponse.SC_CREATED);
		else
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Any required fields are missing or the username provided is already taken.");
			} catch (IOException e) {				
				e.printStackTrace();
			}
	}	
	@DeleteMapping("users/@{username}")
	public void deleteTheUser(@RequestBody Credentials deleteIt, HttpServletResponse response) {		
		if(userService.delete(deleteIt)!=null)
			response.setStatus(HttpServletResponse.SC_OK);
		else
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The username doesn't exist or the password is wrong.");
			} catch (IOException e) {				
				e.printStackTrace();
			}
	}	
	@PatchMapping("users/@{username}")
	public void patchTheUser(@RequestBody Credentials credentials, @RequestBody Profile profile, @PathVariable String username, HttpServletResponse response) {		
		if(userService.patch(credentials, profile, username)!=null)
			response.setStatus(HttpServletResponse.SC_OK);
		else
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The username doesn't exist or the password is wrong.");
			} catch (IOException e) {				
				e.printStackTrace();
			}
	}	
	@PostMapping("users/@{username}/follow")
	public void followTheUser(@RequestBody Credentials credentials, @RequestBody String username, HttpServletResponse response) {		
		if(userService.follow(credentials, username))
			response.setStatus(HttpServletResponse.SC_OK);
		else
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The user doesn't exist, the password is wrong or the user is already followed.");
			} catch (IOException e) {				
				e.printStackTrace();
			}
	}	
	@PostMapping("users/@{username}/unfollow")
	public void unfollowTheUser(@RequestBody Credentials credentials, @RequestBody String username, HttpServletResponse response) {		
		if(userService.unfollow(credentials, username))
			response.setStatus(HttpServletResponse.SC_OK);
		else
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The user doesn't exist, the password is wrong or the user isn't followed.");
			} catch (IOException e) {				
				e.printStackTrace();
			}
	}	
	@GetMapping("users/@{username}/followers")
	public List<UserDto> getFollowers(String username) {
		return userService.getFollowers(username);
	}	
	@GetMapping("users/@{username}/following")
	public List<UserDto> getFollowed(String username) {
		return userService.getFollowed(username);
	}	
	

}