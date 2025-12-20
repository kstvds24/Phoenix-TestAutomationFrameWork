package com.api.services;

import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "job/create" ;
	
	private JobService()
	{
		
	}
	public static Response createJob(Role role, CreateJobPayload createJobPayload)
	{
		return given()
		.spec(RequestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post(CREATE_JOB_ENDPOINT);
	}
}
