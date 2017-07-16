package org.suliga.badlands.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
public class Authorities {
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String authority;
	
	public Authorities() {}
	
	public Authorities(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

