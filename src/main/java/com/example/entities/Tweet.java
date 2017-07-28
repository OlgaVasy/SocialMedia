package com.example.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Tweet {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToMany(mappedBy = "hashTweet")
	private Set<Hashtag> tag;

	@ManyToOne
	private User author;
	private Date posted;

	private String content;

	@ManyToMany
	private Set<User> liked;

	@ManyToOne
	private Tweet repostOf;

	@ManyToOne
	private Tweet inReplyTo;

	public Tweet getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(Tweet inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public Tweet getRepostOf() {
		return repostOf;
	}

	public void setRepostOf(Tweet repostOf) {
		this.repostOf = repostOf;
	}

	private boolean isNotDeleted = true;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getPosted() {
		return posted;
	}

	public void setTimestamp(Date posted) {
		this.posted = posted;
	}

	public boolean isNotDeleted() {
		return isNotDeleted;
	}

	public void setNotDeleted(boolean isNotDeleted) {
		this.isNotDeleted = isNotDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		Tweet other = (Tweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<User> getLiked() {
		return liked;
	}

	public void setLiked(Set<User> liked) {
		this.liked = liked;
	}

	public Set<Hashtag> getTag() {
		return tag;
	}

	public void setTag(Set<Hashtag> tag) {
		this.tag = tag;
	}

}
