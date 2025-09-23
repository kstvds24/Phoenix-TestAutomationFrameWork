package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest()
	{
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization", getToken(FD))
		.and()
		.contentType("")
		.log().headers()
		.log().uri()
		.log().method()
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("$", hasKey("data"))
		.body("$", hasKey("message"))
		.body("data", hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("data.mst_oem.size()", equalTo(2))
		.body("data.mst_oem.size()", greaterThan(0))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
	}
	
	@Test
	public void invalidTokenTest()
	{
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization", "")
		.and()
		.contentType("")
		.log().headers()
		.log().uri()
		.log().method()
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(401);
	}

}
