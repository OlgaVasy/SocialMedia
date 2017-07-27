package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {
	
	public List<Tweet>findByIsNotDeletedTrue();	
	

}
