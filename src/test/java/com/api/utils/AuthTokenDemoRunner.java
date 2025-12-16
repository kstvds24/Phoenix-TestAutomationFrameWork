package com.api.utils;

import com.api.constants.Role;

public class AuthTokenDemoRunner {
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++)
		{
		System.out.println(AuthTokenProvider.getToken(Role.FD));
		}
	}

}
