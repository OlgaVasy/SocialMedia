package com.example.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Hashtag {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany
	private Set<Tweet> hashTweet;

	private String label;
	private Date firstUsed;
	private Date lastUsed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getFirstUsed() {
		return firstUsed;
	}

	public void setFirstUsed(Date firstUsed) {
		this.firstUsed = firstUsed;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public Set<Tweet> getHashTweet() {
		return hashTweet;
	}

	public void setHashTweet(Set<Tweet> hashTweet) {
		this.hashTweet = hashTweet;
	}

}
