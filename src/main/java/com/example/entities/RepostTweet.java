package com.example.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RepostTweet {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Embedded
	private Tweet tweet;
	
	private SimpleTweet repostOf; 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SimpleTweet getRepostOf() {
		return repostOf;
	}
	public void setRepostOf(SimpleTweet repostOf) {
		this.repostOf = repostOf;
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
		RepostTweet other = (RepostTweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
