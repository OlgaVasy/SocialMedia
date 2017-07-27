package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.HashtagDto;
import com.example.dto.SimpleTweetDto;
import com.example.dto.UserDto;
import com.example.service.HashtagService;

@RestController
@RequestMapping("hashtag")
public class HashtagController {
	
	private HashtagService hashtagService;

	public HashtagController(HashtagService hashtagService) {
		this.hashtagService = hashtagService;
	}	
	@GetMapping("validate/tag/exists/{label}")
	public boolean hashtagExists(@PathVariable String label) {
		return hashtagService.tagExists(label);
	}
	@GetMapping("tags")
	public List<HashtagDto> getHashtags() {
		return hashtagService.getAll();
	}	
		
	}


