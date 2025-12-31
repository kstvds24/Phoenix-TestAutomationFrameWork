package com.dataproviders.api.bean;

import com.poiji.annotation.ExcelCellName;

public class UserBean {

	@ExcelCellName("username")
	private String username;
	@ExcelCellName("password")
	private String password;
	
	public UserBean()
	{
		
	}
	
	public UserBean(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "UserPOJO [username=" + username + ", password=" + password + "]";
	}
	
	
}
