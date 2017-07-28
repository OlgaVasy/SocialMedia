package com.example.dto;

import java.sql.Date;

import com.example.entities.User;

public class TweetDto {

	private User author;
	private Date posted;

	public TweetDto() {
	}

	public TweetDto(User author, Date posted) {
		super();
		this.setAuthor(author);
		this.setPosted(posted);
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

}
