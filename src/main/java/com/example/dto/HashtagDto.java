package com.example.dto;

import java.sql.Date;

public class HashtagDto {
	
	private String label;
	private Date firstUsed;
	private Date lastUsed;
	
	public HashtagDto() {		
	}
	
	public HashtagDto(String label, Date firstUsed, Date lastUsed) {
		super();
		this.setLabel(label);
		this.setFirstUsed(firstUsed);	
		this.setLastUsed(lastUsed);
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
