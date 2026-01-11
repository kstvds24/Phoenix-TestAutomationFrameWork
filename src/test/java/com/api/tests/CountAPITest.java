package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.DashboardService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.api.utils.SpecUtil.*;
@Listeners(com.listeners.APITestListeners.class)
@Epic("Job Management")
@Feature("Job Count")
public class CountAPITest {
	
	@Story("Job count data is shown correctly")
	@Description("Verify if count api is giving correct response")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verifying if count api test is giving correct response", groups= {"api","regression","smoke"})
	public void verifyCountAPIResponse()
	{
		DashboardService.count(FD)
		.then()
		.spec(ResponseSpec_JSON(200))
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", everyItem(not(blankOrNullString())))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"))
		.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
	}
	@Story("Job count data is shown correctly")
	@Description("Verifying if count api test is giving 500 status code for No Auth")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verifying if count api test is giving 500 status code for No Auth", groups= {"api","regression","smoke","negative"})
	public void countAPITest_MissingAuthToken()
	{
		given()
        .spec(RequestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(ResponseSpec_TEXT(401));
	}

}
