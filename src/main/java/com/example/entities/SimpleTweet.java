package com.example.entities;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SimpleTweet {	
		
	@Id
	@GeneratedValue
	private Integer simpleId;
	
	@Embedded
	private Tweet tweet;

	private String content;	
	
	@OneToMany(mappedBy = "repostOf")
	private Set<RepostTweet> reposts;	
	@OneToMany(mappedBy = "inReplyTo")
	private Set<ReplyTweet> replies;	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSimpleId() {
		return simpleId;
	}
	public void setSimpleId(Integer simpleId) {
		this.simpleId = simpleId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((simpleId == null) ? 0 : simpleId.hashCode());
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
		SimpleTweet other = (SimpleTweet) obj;
		if (simpleId == null) {
			if (other.simpleId != null)
				return false;
		} else if (!simpleId.equals(other.simpleId))
			return false;
		return true;
	}
	public Set<ReplyTweet> getReplies() {
		return replies;
	}
	public void setReplies(Set<ReplyTweet> replies) {
		this.replies = replies;
	}
	public Set<RepostTweet> getReposts() {
		return reposts;
	}
	public void setReposts(Set<RepostTweet> reposts) {
		this.reposts = reposts;
	}
	public Tweet getTweet() {
		return tweet;
	}
	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}
	
	

}
