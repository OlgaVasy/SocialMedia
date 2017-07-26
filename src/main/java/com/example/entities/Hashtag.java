package com.example.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hashtag {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String label;
	private Date firstUsed;
	private Date lastUsed;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Date getFirstUsed() {
		return firstUsed;
	}
	public void setFirstUsed(Date firstUsed) {
		this.firstUsed = firstUsed;
	}
	public Date getLastUsed() {
		return lastUsed;
	}
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}
