package com.example.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RepostTweet {

	@Id
	@GeneratedValue
	private Integer repostId;
	
	@Embedded
	private Tweet tweet;	
	
	@ManyToOne
	private SimpleTweet repostOf; 

	public SimpleTweet getRepostOf() {
		return repostOf;
	}
	public void setRepostOf(SimpleTweet repostOf) {
		this.repostOf = repostOf;
	}
	public Integer getRepostId() {
		return repostId;
	}
	public void setRepostId(Integer repostId) {
		this.repostId = repostId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((repostId == null) ? 0 : repostId.hashCode());
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
		RepostTweet other = (RepostTweet) obj;
		if (repostId == null) {
			if (other.repostId != null)
				return false;
		} else if (!repostId.equals(other.repostId))
			return false;
		return true;
	}
	
	

}
