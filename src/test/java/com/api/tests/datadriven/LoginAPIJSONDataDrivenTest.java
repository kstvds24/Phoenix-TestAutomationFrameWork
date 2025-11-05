package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.RequestSpec;
import static com.api.utils.SpecUtil.ResponseSpec_JSON;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import com.api.request.model.UserCredentials;

public class LoginAPIJSONDataDrivenTest {

	@Test(description = "Verifying if login api is working fine for FD User", groups = { "api", "regression",
			"datadriven",
			"csv" }, dataProviderClass = com.dataproviders.DataProvidersUtils.class, dataProvider = "LoginAPIJsonDataProvider")
	public void loginAPITest(UserCredentials userCredentials) {
		// Rest Assured Code
		given().spec(RequestSpec(userCredentials)).when().post("login").then().spec(ResponseSpec_JSON(200)).and()
				.body("message", equalTo("Success"))
				.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

}
