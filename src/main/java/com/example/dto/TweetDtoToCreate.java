package com.example.dto;

import com.example.entities.Credentials;

public class TweetDtoToCreate {

	private Credentials credentials;
	private String content;

	public TweetDtoToCreate() {
	}

	public TweetDtoToCreate(Credentials credentials, String post) {
		super();
		this.setCredentials(credentials);
		this.setContent(content);
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
