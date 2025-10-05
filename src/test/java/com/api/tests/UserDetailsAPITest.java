package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {
	
	@Test(description="Verify if user details api response is shown correctly",groups= {"api","regression","smoke"})
	public void userDetailsAPITest()
	{
		//System.out.println("asd");
		//System.out.println(getToken("fd"));
		//Header auth = new Header("Authorization", getToken(FD));
		
		 given().spec(RequestSpecWithAuth(FD))
		 .when()
		 .get("userdetails")
		 .then()
		 .spec(ResponseSpec_JSON(200))
		 .and()
		 .body("message", equalTo("Success"))
		 .and()
		 .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
