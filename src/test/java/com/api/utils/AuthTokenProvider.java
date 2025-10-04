package com.api.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider()
	{
		
	}

	public static String getToken(Role role) {
	
		//String role = "fd";
	 UserCredentials user = null;
	 if(role == Role.FD)
	 {
		 user = new UserCredentials("iamfd", "password");
	 }
	 else if(role == Role.SUP)
	 {
		 user = new UserCredentials("iamsup", "password");
	 }
	 else if(role == Role.ENG)
	 {
		 user = new UserCredentials("iameng", "password");
	 }
	 else if(role == Role.QC)
	 {
		 user = new UserCredentials("iamqc", "password");
	 }
	 String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON).body(user).log().uri().log().body().log().headers().post("login").then()
	 .log().all().statusCode(200).body("message", equalTo("Success")).body("data.token", notNullValue()).extract().jsonPath().getString("data.token");
	System.out.println(token);
	 return token;
	}

}
