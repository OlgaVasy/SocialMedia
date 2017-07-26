package com.example.dto;

import java.sql.Date;

import com.example.entities.SimpleTweet;
import com.example.entities.User;

public class RepostTweetDto {
	
	private User author;
	private Date posted;
	private SimpleTweet repostOf;
	
	public RepostTweetDto() {		
	}
	
	public RepostTweetDto(User author, Date posted, SimpleTweet repostOf) {
		super();
		this.setAuthor(author);
		this.setPosted(posted);	
		this.setRepostOf(repostOf);
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}

	public SimpleTweet getRepostOf() {
		return repostOf;
	}

	public void setRepostOf(SimpleTweet repostOf) {
		this.repostOf = repostOf;
	}

}
