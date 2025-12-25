package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import com.api.request.model.Details;
import static com.api.services.DashboardService.*;

public class DetailsAPITest {
	private Details details;
	@BeforeTest
	public void initialize()
	{
		details = new Details("created_today");
	}
	@Test(description = "Verify if Details API is working fine", groups = {"api", "regression"})
	public void detailsAPITest()
	{
		details(FD, details)
		.then()
		.statusCode(200)
		.body("message", equalTo("Success"));
	}

}
