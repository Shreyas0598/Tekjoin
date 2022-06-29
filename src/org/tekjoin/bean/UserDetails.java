package org.tekjoin.bean;

import java.io.Serializable;

public class UserDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private int userId;
	private String password;
	private String email;
	public String getName() {
		return username;
	}
	public void setName(String username) {
		this.username = username;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}
