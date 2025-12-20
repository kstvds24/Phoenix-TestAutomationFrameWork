package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.services.MasterService;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {
	
	@Test(description="Verify that master api returns correct response", groups= {"api","regression","smoke"})
	public void masterAPITest()
	{
		MasterService.master(FD)
		.then()
		.spec(ResponseSpec_JSON(200))
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
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
	}
	
	@Test(description="Verify that master api returns correct response code for invalid token", groups= {"api","regression","smoke","negative"})
	public void invalidTokenTest()
	{
		given()
		.spec(RequestSpec())
		.when()
		.post("master")
		.then()
		.spec(ResponseSpec_TEXT(401));
	}

}
