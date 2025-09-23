package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest()
	{
		//System.out.println("asd");
		//System.out.println(getToken("fd"));
		Header auth = new Header("Authorization", getToken(FD));
		
		 given().baseUri(getProperty("BASE_URI"))
		 .and()
		 .header(auth)
		 .and()
		 .accept(ContentType.ANY)
		 .log().uri()
		 .log().headers()
		 .log().method()
		 .log().body()
		 .when()
		 .get("userdetails")
		 .then()
		 .statusCode(200)
		 .log().all()
		 .time(lessThan(1000L))
		 .and()
		 .body("message", equalTo("Success"))
		 .and()
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
