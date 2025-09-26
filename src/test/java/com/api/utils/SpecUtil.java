package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;

import org.hamcrest.Matchers;

import com.api.constants.Role;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	//GET and DEl
	public static RequestSpecification RequestSpec() {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI")).
		setContentType(ContentType.JSON).setAccept(ContentType.JSON)
		.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS)
		.log(LogDetail.BODY).build();
		
		return spec;

	}
	
	public static RequestSpecification RequestSpecWithAuth(Role role) {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
		.addHeader("Authorization", AuthTokenProvider.getToken(role)).
		setContentType(ContentType.JSON).setAccept(ContentType.JSON)
		.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS)
		.log(LogDetail.BODY).build();
		
		return spec;

	}
	
	//POST & PUT
	public static RequestSpecification RequestSpec(Object payload) {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI")).
		setContentType(ContentType.JSON).setAccept(ContentType.JSON)
		.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS)
		.log(LogDetail.BODY).setBody(payload).build();
		
		return spec;

	}
	
	public static ResponseSpecification ResponseSpec_JSON(int status)
	{
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(status).
		expectContentType(ContentType.JSON).expectResponseTime(Matchers.lessThan(1500L))
		.log(LogDetail.ALL).build();
		return respSpec;
	}
	public static ResponseSpecification ResponseSpec_TEXT(int status)
	{
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(status).
		expectResponseTime(Matchers.lessThan(1500L))
		.log(LogDetail.ALL).build();
		return respSpec;
	}

}
