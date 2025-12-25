package com.api.services;

import static com.api.utils.SpecUtil.RequestSpec;
import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class DashboardService {
	
    private static final String COUNT_ENDPOINT = "/dashboard/count" ;
    private static final String DETAILS_ENDPOINT = "/dashboard/details";
	private DashboardService()
	{
		
	}
	public static Response count(Role role)
	{
		return given()
		.spec(RequestSpecWithAuth(role))
		.when()
		.get(COUNT_ENDPOINT);
	}
	public static Response countWithNoAuth()
	{
		return given()
        .spec(RequestSpec())
		.when()
		.get(COUNT_ENDPOINT);
	}
	public static Response details(Role role,Object payload)
	{
		return given()
		.spec(RequestSpecWithAuth(role, payload))
		.when()
		.post(DETAILS_ENDPOINT);
	}
}
