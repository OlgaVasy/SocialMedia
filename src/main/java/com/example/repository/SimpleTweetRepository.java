package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.SimpleTweet;

public interface SimpleTweetRepository extends JpaRepository<SimpleTweet, Integer>{

}
