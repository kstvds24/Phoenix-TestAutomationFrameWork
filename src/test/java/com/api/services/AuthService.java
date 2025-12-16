package com.api.services;

import static com.api.utils.SpecUtil.RequestSpec;
import static io.restassured.RestAssured.given;

import com.api.request.model.UserCredentials;

import io.restassured.response.Response;

public class AuthService {
	private static final String LOGIN_ENDPONT = "login";
	private AuthService()
	{
		
	}
	public static Response Login(UserCredentials userCredentials)
	{
		return given()
		.spec(RequestSpec(userCredentials))
		.when()
		.post(LOGIN_ENDPONT);
	}

}
