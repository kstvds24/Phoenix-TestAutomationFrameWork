package com.api.services;

import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class DashboardService {
	
    private static final String COUNT_ND_POINT = "/dashboard/count" ;
	private DashboardService()
	{
		
	}
	public static Response count(Role role)
	{
		return given()
		.spec(RequestSpecWithAuth(role))
		.when()
		.get(COUNT_ND_POINT);
	}
}
