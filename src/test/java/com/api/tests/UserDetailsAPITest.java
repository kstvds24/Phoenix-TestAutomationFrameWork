package com.api.tests;

import static com.api.utils.SpecUtil.ResponseSpec_JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.UserDetailsService;

public class UserDetailsAPITest {

	@Test(description = "Verify if user details api response is shown correctly", groups = { "api", "regression",
			"smoke" })
	public void userDetailsAPITest() {

		UserDetailsService.userDetails(Role.FD).then().spec(ResponseSpec_JSON(200)).and()
				.body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
