package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.HashtagDto;
import com.example.mapper.HashtagMapper;
import com.example.repository.HashtagRepository;

@Service
public class HashtagService {

	private HashtagMapper hashtagMapper;
	private HashtagRepository hashtagRepo;

	public HashtagService(HashtagMapper hashtagMapper, HashtagRepository hashtagRepo) {
		this.hashtagMapper = hashtagMapper;
		this.hashtagRepo = hashtagRepo;
	}

	public boolean tagExists(String label) {
		return hashtagRepo.findByLabel(label) != null;
	}

	public HashtagDto findById(Integer id) {
		return hashtagMapper.toDto(hashtagRepo.getOne(id));
	}

	public List<HashtagDto> getAll() {
		return hashtagRepo.findAll().stream().map(hashtagMapper::toDto).collect(Collectors.toList());
	}

}
