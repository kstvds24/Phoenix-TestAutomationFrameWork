package com.api.tests;

import static com.api.utils.SpecUtil.ResponseSpec_JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.APITestListeners.class)
@Epic("User Management")
@Feature("Authentication")

public class LoginAPITest {
	
	
	private UserBean user;
	@BeforeMethod(description = "Creating the User Payload")
	public void setup()
	{
		user = new UserBean("iamfd", "password");
	}
	@Story("Valid User should be able to login")
	@Description("Verify if FD user is able to login via api")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verifying if login api is working fine for FD User",groups = {"api","regression","smoke"}, retryAnalyzer = com.api.retry.RetryAnalyzer.class)
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
