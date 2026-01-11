package com.api.services;

import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "job/create";
	private static final String SEARCH_JOB_ENDPOINT = "job/search";
	private static final Logger LOGGER = LogManager.getLogger(JobService.class);

	private JobService() {

	}

	@Step("Creating InWarranty Job with Create Job API")
	public static Response createJob(Role role, CreateJobPayload createJobPayload) {
		LOGGER.info("Making request to the {} for the role {} with payload {}", CREATE_JOB_ENDPOINT, role,
				createJobPayload);
		return given().spec(RequestSpecWithAuth(role, createJobPayload)).when().post(CREATE_JOB_ENDPOINT);
	}

	@Step("Making Search API Request")
	public static Response searchJob(Role role, Object searchJobPayload) {
		LOGGER.info("Making request to the {} for the role {} with payload {}", CREATE_JOB_ENDPOINT, role,
				searchJobPayload);
		return given().spec(RequestSpecWithAuth(role, searchJobPayload)).when().post(SEARCH_JOB_ENDPOINT);
	}
}
