package com.api.request.model;

import com.poiji.annotation.ExcelCellName;

public class UserCredentials {
	public UserCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	@ExcelCellName("username")
	String username;
	@ExcelCellName("password")
	String password;
	public UserCredentials()
	{
		
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
	
}
