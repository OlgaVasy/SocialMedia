package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ReplyTweet;

public interface ReplyTweetRepository extends JpaRepository<ReplyTweet, Integer>{

}
