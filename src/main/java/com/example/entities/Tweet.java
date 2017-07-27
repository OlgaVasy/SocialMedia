package com.example.entities;

import java.sql.Date;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Embeddable
public class Tweet {
	
	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private User author;
	private Date posted;	
	
	private boolean isNotDeleted = true;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}	
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
	public boolean isNotDeleted() {
		return isNotDeleted;
	}
	public void setNotDeleted(boolean isNotDeleted) {
		this.isNotDeleted = isNotDeleted;
	}
}
