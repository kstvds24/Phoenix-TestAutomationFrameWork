package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.services.DashboardService.details;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.Details;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListeners.class)
@Epic("Job Management")
@Feature("Job Details")
public class DetailsAPITest {
	private Details details;
	@BeforeMethod(description = "Creating Details Payload")
	public void initialize()
	{
		details = new Details("created_today");
	}
	@Test(description = "Verify if Details API is working fine", groups = {"api", "regression"})
	@Story("Job Details is shown correctly for FD")
	@Description("Verify if Details API is working properly")
	@Severity(SeverityLevel.BLOCKER)
	public void detailsAPITest()
	{
		details(FD, details)
		.then()
		.statusCode(200)
		.body("message", equalTo("Success"));
	}

}
