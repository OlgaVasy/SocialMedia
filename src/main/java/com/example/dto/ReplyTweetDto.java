package com.example.dto;

import java.sql.Date;

import com.example.entities.SimpleTweet;
import com.example.entities.User;

public class ReplyTweetDto {
	
	private User author;
	private Date posted;
	private SimpleTweet inReplyTo;
	
	public ReplyTweetDto() {		
	}
	
	public ReplyTweetDto(User author, Date posted, SimpleTweet inReplyTo) {
		super();
		this.setAuthor(author);
		this.setPosted(posted);	
		this.setInReplyTo(inReplyTo);
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

	public SimpleTweet getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(SimpleTweet inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

}
