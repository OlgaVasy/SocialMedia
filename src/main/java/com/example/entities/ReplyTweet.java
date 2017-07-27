package com.example.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ReplyTweet {

	@Id
	@GeneratedValue
	private Integer replyId;
	
	@Embedded
	private Tweet tweet;

	@ManyToOne
	private SimpleTweet inReplyTo;
	

	public SimpleTweet getInReplyTo() {
		return inReplyTo;
	}
	public void setInReplyTo(SimpleTweet inReplyTo) {
		this.inReplyTo = inReplyTo;
	}
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((replyId == null) ? 0 : replyId.hashCode());
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
		if (replyId == null) {
			if (other.replyId != null)
				return false;
		} else if (!replyId.equals(other.replyId))
			return false;
		return true;
	}	

}
