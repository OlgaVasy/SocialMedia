package com.example.service;

import com.example.mapper.HashtagMapper;
import com.example.repository.HashtagRepository;

public class HashtagService {
	
	private HashtagMapper hashtagMapper;
	private HashtagRepository hashtagRepo;

	public HashtagService(HashtagMapper hashtagMapper, HashtagRepository hashtagRepo) {
		this.hashtagMapper = hashtagMapper;
		this.hashtagRepo = hashtagRepo;
	}

}
