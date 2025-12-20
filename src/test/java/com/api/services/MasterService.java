package com.api.services;

import com.api.constants.Role;

import io.restassured.response.Response;

import static com.api.utils.SpecUtil.*;

import static io.restassured.RestAssured.*;

public class MasterService {

	private static String MASTER_ENDPOINT = "master";
	
	private MasterService()
	{
		
	}
	
	public static Response master(Role role)
	{
		return given()
		.spec(RequestSpecWithAuth(role))
		.when()
		.post(MASTER_ENDPOINT);
	}
}
