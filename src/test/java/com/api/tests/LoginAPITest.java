package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;


public class LoginAPITest {
	
	@Test
	public void loginAPITest()
	{
		//Rest Assured Code
		UserCredentials user = new UserCredentials("iamfd", "password");
		given()
		.spec(SpecUtil.RequestSpec(user))
		.when()
		.post("login")
		.then()
		.spec(SpecUtil.ResponseSpec_JSON(200))
		.and()
		.body("message", equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

}
