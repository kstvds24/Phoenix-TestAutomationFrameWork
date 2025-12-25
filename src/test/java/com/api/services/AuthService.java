package com.api.services;

import static com.api.utils.SpecUtil.RequestSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class AuthService {
	private static final String LOGIN_ENDPONT = "login";
	private AuthService()
	{
		
	}
	public static Response Login(Object userCredentials)
	{
		return given()
		.spec(RequestSpec(userCredentials))
		.when()
		.post(LOGIN_ENDPONT);
	}

}
