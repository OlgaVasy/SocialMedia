package com.example.dto;

import com.example.entities.Credentials;
import com.example.entities.Profile;

public class UserDtoToCreate {

	private Credentials credentials;
	private Profile profile;

	public UserDtoToCreate() {
	}

	public UserDtoToCreate(Credentials credentials, Profile profile) {
		super();
		this.setCredentials(credentials);
		this.setProfile(profile);
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

}
