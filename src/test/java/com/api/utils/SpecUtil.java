package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.filters.SensitiveDataFilter;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	// GET and DEl
	@Step("Setting up BaseURI, Content Type as Application/JSON and attaching the sensitive data filter")
	public static RequestSpecification RequestSpec() {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();

		return spec;

	}
	@Step("Setting up BaseURI, Content Type as Application/JSON and attaching the sensitive data filter for a role")
	public static RequestSpecification RequestSpecWithAuth(Role role) {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.addHeader("Authorization", AuthTokenProvider.getToken(role)).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured()).build();

		return spec;

	}
	@Step("Setting up BaseURI, Content Type as Application/JSON and attaching the sensitive data filter for a role and Payload")
	public static RequestSpecification RequestSpecWithAuth(Role role, Object payload) {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.addHeader("Authorization", AuthTokenProvider.getToken(role)).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).setBody(payload).addFilter(new SensitiveDataFilter()).addFilter(new AllureRestAssured()).build();

		return spec;

	}

	@Step("Setting up BaseURI, Content Type as Application/JSON and attaching the sensitive data filter for a Payload")
	public static RequestSpecification RequestSpec(Object payload) {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).addFilter(new SensitiveDataFilter())
				.setBody(payload).build();

		return spec;

	}
	@Step("Expecting the response to have Content TType as Application/JSON, Response Time less than 1000 ms and Status Code")
	public static ResponseSpecification ResponseSpec_JSON(int status) {
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(status)
				.expectContentType(ContentType.JSON).expectResponseTime(Matchers.lessThan(1500L)).build();
		return respSpec;
	}
	@Step("Expecting the response to have Content TType as Application/JSON, Response Time less than 1000 ms and Status Code")
	public static ResponseSpecification ResponseSpec_TEXT(int status) {
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(status)
				.expectResponseTime(Matchers.lessThan(1500L)).build();
		return respSpec;
	}

}
