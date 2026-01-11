package com.api.services;

import static com.api.utils.SpecUtil.RequestSpec;
import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DashboardService {
	
    private static final String COUNT_ENDPOINT = "/dashboard/count" ;
    private static final String DETAILS_ENDPOINT = "/dashboard/details";
    private static final Logger LOGGER = LogManager.getLogger(DashboardService.class);
	private DashboardService()
	{
		
	}
	@Step("Making Count API Request for the role")
	public static Response count(Role role)
	{
		LOGGER.info("Making request to the {} for the role {}",COUNT_ENDPOINT,role);
		return given()
		.spec(RequestSpecWithAuth(role))
		.when()
		.get(COUNT_ENDPOINT);
	}
	@Step("Making Count API Request without Auth Token")
	public static Response countWithNoAuth()
	{
		LOGGER.info("Making request to the {} with No Auth",COUNT_ENDPOINT);
		return given()
        .spec(RequestSpec())
		.when()
		.get(COUNT_ENDPOINT);
	}
	@Step("Making Details API Request")
	public static Response details(Role role,Object payload)
	{
		LOGGER.info("Making request to the {} for the role {} with payload {}",DETAILS_ENDPOINT,role,payload);
		return given()
		.spec(RequestSpecWithAuth(role, payload))
		.when()
		.post(DETAILS_ENDPOINT);
	}
}
