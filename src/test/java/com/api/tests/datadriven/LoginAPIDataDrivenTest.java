package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class LoginAPIDataDrivenTest {
	
	
	

	@Test(description = "Verifying if login api is working fine for FD User",
			groups = {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProvidersUtils.class,
			dataProvider = "LoginAPIDataProvider"
			)
	public void loginAPITest(UserBean user)
	{
		//Rest Assured Code
		given()
		.spec(RequestSpec(user))
		.when()
		.post("login")
		.then()
		.spec(ResponseSpec_JSON(200))
		.and()
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

}
