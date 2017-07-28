package com.example.dto;

import java.sql.Date;

import com.example.entities.Tweet;
import com.example.entities.User;

public class RepostTweetDto {

	private User author;
	private Date posted;
	private Tweet repostOf;

	public RepostTweetDto() {
	}

	public RepostTweetDto(User author, Date posted, Tweet repostOf) {
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

	public Tweet getRepostOf() {
		return repostOf;
	}

	public void setRepostOf(Tweet repostOf) {
		this.repostOf = repostOf;
	}

}
