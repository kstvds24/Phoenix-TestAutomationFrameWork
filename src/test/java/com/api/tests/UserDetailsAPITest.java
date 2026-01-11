package com.api.tests;

import static com.api.utils.SpecUtil.ResponseSpec_JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.UserDetailsService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListeners.class)
@Epic("User Management")
@Feature("User Details")
public class UserDetailsAPITest {

	@Test(description = "Verify if user details api response is shown correctly", groups = { "api", "regression",
			"smoke" })
	@Story("UserDetails should be shown")
	@Description("Verify if the Userdetails API response is shown correctly")
	@Severity(SeverityLevel.CRITICAL)
	public void userDetailsAPITest() {

		UserDetailsService.userDetails(Role.FD).then().spec(ResponseSpec_JSON(200)).and()
				.body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
