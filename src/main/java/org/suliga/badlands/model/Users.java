package org.suliga.badlands.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Users {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	private boolean enabled;
	
	@OneToOne
	@JoinColumn(name="FK_AUTHORITIES_ID")
	private Authorities authorities;
	
	public Users() {}
	
	public Users(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	public void hashPassword() {
		password = new BCryptPasswordEncoder().encode(password);
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}


