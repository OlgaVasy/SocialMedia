package com.example.dto;

import java.sql.Date;

import com.example.entities.User;

public class SimpleTweetDto {

	private User author;
	private Date posted;
	private String content;

	public SimpleTweetDto() {
	}

	public SimpleTweetDto(User author, Date posted, String content) {
		super();
		this.setAuthor(author);
		this.setPosted(posted);
		this.setContent(content);
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
