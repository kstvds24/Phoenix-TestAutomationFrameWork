package com.api.services;

import com.api.constants.Role;

import io.restassured.response.Response;

import static com.api.utils.SpecUtil.*;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MasterService {

	private static String MASTER_ENDPOINT = "master";
	private static final Logger LOGGER = LogManager.getLogger(MasterService.class);

	private MasterService() {

	}

	public static Response master(Role role) {
		LOGGER.info("Making request to the {} for the role {}", MASTER_ENDPOINT, role);
		return given().spec(RequestSpecWithAuth(role)).when().post(MASTER_ENDPOINT);
	}
}
