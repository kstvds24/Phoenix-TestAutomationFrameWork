package com.api.services;

import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "job/create" ;
	private static final String SEARCH_JOB_ENDPOINT = "job/search" ;
	
	private JobService()
	{
		
	}
	public static Response createJob(Role role, CreateJobPayload createJobPayload)
	{
		return given()
		.spec(RequestSpecWithAuth(role, createJobPayload))
		.when()
		.post(CREATE_JOB_ENDPOINT);
	}
	public static Response searchJob(Role role, Object searchJobPayload)
	{
		return given()
		.spec(RequestSpecWithAuth(role, searchJobPayload))
		.when()
		.post(SEARCH_JOB_ENDPOINT);
	}
}
