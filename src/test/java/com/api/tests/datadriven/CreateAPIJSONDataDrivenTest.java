package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.ResponseSpec_JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.services.JobService;
@Listeners(com.listeners.APITestListeners.class)
public class CreateAPIJSONDataDrivenTest {

	@Test(description = "Verifying if create job api is working fine", groups = { "api", "regression",
			"datadriven",
			"csv" }, dataProviderClass = com.dataproviders.DataProvidersUtils.class, dataProvider = "CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) {
		// Rest Assured Code
		JobService.createJob(Role.FD, createJobPayload)
		.then()
		.spec(ResponseSpec_JSON(200))
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
	}

}
