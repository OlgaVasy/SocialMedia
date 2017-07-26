package com.example.entities;

import java.util.HashSet;
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
	private Integer id;
	
	@Embedded
	private Tweet tweet;
	
	private String content;
	
	@OneToMany(mappedBy = "repostOf")
	private Set<RepostTweet> reposts = new HashSet<RepostTweet>();	
	
	@OneToMany(mappedBy = "inReplyTo")
	private Set<ReplyTweet> replies = new HashSet<ReplyTweet>();	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		SimpleTweet other = (SimpleTweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
