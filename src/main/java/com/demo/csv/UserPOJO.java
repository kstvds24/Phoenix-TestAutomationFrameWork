package com.demo.csv;

public class UserPOJO {

	private String username;
	private String password;
	
	public UserPOJO()
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
