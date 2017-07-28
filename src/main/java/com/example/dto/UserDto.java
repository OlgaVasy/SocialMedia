package com.example.dto;

import java.sql.Date;

import com.example.entities.Profile;

public class UserDto {

	private String username;
	private Profile profile;
	private Date timestamp;

	public UserDto() {
	}

	public UserDto(String username, Profile profile, Date timestamp) {
		super();
		this.setUsername(username);
		this.setProfile(profile);
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
