package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest()
	{
		//System.out.println("asd");
		//System.out.println(getToken("fd"));
		//Header auth = new Header("Authorization", getToken(FD));
		
		 given().spec(SpecUtil.RequestSpecWithAuth(FD))
		 .when()
		 .get("userdetails")
		 .then()
		 .spec(SpecUtil.ResponseSpec_JSON(200))
		 .and()
		 .body("message", equalTo("Success"))
		 .and()
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
