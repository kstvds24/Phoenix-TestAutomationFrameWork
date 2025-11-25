package com.api.tests.datadriven;
import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static com.api.utils.SpecUtil.ResponseSpec_JSON;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;

public class CreateJobAPIExcelDataDrivenTest {
	
	
	
	@Test(description="Verify if Create Job API is able to create in-warranty jobs",groups= {"api","regression","smoke","csv"},
			dataProviderClass = com.dataproviders.DataProvidersUtils.class,
			dataProvider = "CreatejobAPIExcelDataProvider")
	public void createJobApiTest(CreateJobPayload createJobPayload)
	{
		given()
		.spec(RequestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("job/create")
		.then()
		.spec(ResponseSpec_JSON(200))
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
	}

}
