package com.dataproviders.api.bean;

public class UserBean {

	private String username;
	private String password;
	
	public UserBean()
	{
		
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
