package com.example.entities;

import java.sql.Date;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Tweet {

	@ManyToOne
	private User author;
	private Date posted;
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getPosted() {
		return posted;
	}
	public void setTimestamp(Date posted) {
		this.posted = posted;
	}
}
