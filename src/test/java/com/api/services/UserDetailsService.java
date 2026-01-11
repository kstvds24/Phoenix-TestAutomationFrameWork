package com.api.services;

import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserDetailsService {

	private UserDetailsService() {

	}

	private static final String USER_DETAILS_ENDPOINT = "userdetails";
	private static final Logger LOGGER = LogManager.getLogger(UserDetailsService.class);

	@Step("Making User Details API Request")
	public static Response userDetails(Role role) {
		LOGGER.info("Making request to the {} for the role {}", USER_DETAILS_ENDPOINT, role);
		return given().spec(RequestSpecWithAuth(role)).when().get(USER_DETAILS_ENDPOINT);
	}
}
