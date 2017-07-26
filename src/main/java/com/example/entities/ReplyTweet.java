package com.example.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReplyTweet {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Embedded
	private Tweet tweet;
	
	private SimpleTweet inReplyTo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SimpleTweet getInReplyTo() {
		return inReplyTo;
	}
	public void setInReplyTo(SimpleTweet inReplyTo) {
		this.inReplyTo = inReplyTo;
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
		ReplyTweet other = (ReplyTweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
