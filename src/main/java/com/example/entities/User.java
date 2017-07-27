package com.example.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "media_user")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;	
	
	private Date timestamp;
	
	@Embedded
	private Profile profile;

	@Embedded
	private Credentials credentials;	
	
	private boolean isAvailable;
	
	@ManyToMany(mappedBy = "followed")
	private Set<User>followers;	
	private Set<User>followed;
	
	@OneToMany(mappedBy = "author")
	private Set<Tweet>tweets;
	@OneToMany
	private Set<SimpleTweet>mentions;
	@ManyToMany(mappedBy = "liked")
	private Set<Tweet>likes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Date timestamp(){
		return new Date(System.currentTimeMillis());
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public Set<User> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	public Set<User> getFollowed() {
		return followed;
	}
	public void setFollowed(Set<User> followed) {
		this.followed = followed;
	}
	public Set<SimpleTweet> getMentions() {
		return mentions;
	}
	public void setMentions(Set<SimpleTweet> mentions) {
		this.mentions = mentions;
	}
	public Set<Tweet> getTweets() {
		return tweets;
	}
	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}
	public Set<Tweet> getLikes() {
		return likes;
	}
	public void setLikes(Set<Tweet> likes) {
		this.likes = likes;
	}
}
