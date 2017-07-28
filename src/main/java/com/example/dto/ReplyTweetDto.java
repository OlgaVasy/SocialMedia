package com.example.dto;

import java.sql.Date;
import java.util.Set;

import javax.persistence.ManyToMany;
import com.example.entities.Tweet;
import com.example.entities.User;

public class ReplyTweetDto {

	private User author;
	private Date posted;
	private Tweet inReplyTo;

	@ManyToMany
	private Set<User> liked;

	public ReplyTweetDto() {
	}

	public ReplyTweetDto(User author, Date posted, Tweet inReplyTo) {
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

	public Tweet getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(Tweet inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

}
