package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.SimpleTweet;

public interface SimpleTweetRepository extends JpaRepository<SimpleTweet, Integer>{
	
	public List<SimpleTweet>findByTweetIsNotDeletedTrue();

}
