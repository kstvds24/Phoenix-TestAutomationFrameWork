package com.api.services;

import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class UserDetailsService {

	private UserDetailsService() {

	}

	private static final String USER_DETAILS_ENDPOINT = "userdetails";

	public static Response userDetails(Role role) {
		return given().spec(RequestSpecWithAuth(role)).when().get(USER_DETAILS_ENDPOINT);
	}
}
