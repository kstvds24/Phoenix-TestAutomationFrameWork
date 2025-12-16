package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class LoginAPITest {
	
	
	private UserCredentials user;
	@BeforeMethod
	public void setup()
	{
		user = new UserCredentials("iamfd", "password");
	}
	@Test(description = "Verifying if login api is working fine for FD User",groups = {"api","regression","smoke"})
	public void loginAPITest()
	{
		//Rest Assured Code
		AuthService.Login(user)
		.then()
		.spec(ResponseSpec_JSON(200))
		.and()
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

}
