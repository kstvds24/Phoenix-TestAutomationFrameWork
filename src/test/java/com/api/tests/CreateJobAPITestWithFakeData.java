package com.api.tests;

import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static com.api.utils.SpecUtil.ResponseSpec_JSON;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.utils.FakeDataGenerator;
import com.database.dao.CustomerDao;
import com.database.model.CustomerDBModel;

public class CreateJobAPITestWithFakeData {
	CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Creating create job api payload")
	public void setup() {
		createJobPayload = FakeDataGenerator.generateFakeCreateJobData();
	}

	@Test(description = "Verify if Create Job API is able to create in-warranty jobs", groups = { "api", "regression",
			"smoke" })
	public void createJobApiTest() {
		int customerId = given().spec(RequestSpecWithAuth(Role.FD, createJobPayload)).when().post("job/create").then()
				.spec(ResponseSpec_JSON(200))
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_")).extract().body().jsonPath().getInt("data.tr_customer_id");
		CustomerDBModel actualCustomerDataFromDB = CustomerDao.getCustomerInfo(customerId);
		Customer expectedCustomerData = createJobPayload.customer();
		System.out.println(customerId);
		Assert.assertEquals(expectedCustomerData.first_name(), actualCustomerDataFromDB.getFirst_name());
		Assert.assertEquals(expectedCustomerData.last_name(), actualCustomerDataFromDB.getLast_name());
		Assert.assertEquals(expectedCustomerData.mobile_number(), actualCustomerDataFromDB.getMobile_number());
		Assert.assertEquals(expectedCustomerData.mobile_number_alt(), actualCustomerDataFromDB.getMobile_number_alt());
		Assert.assertEquals(expectedCustomerData.email_id(), actualCustomerDataFromDB.getEmail_id());
		Assert.assertEquals(expectedCustomerData.email_id_alt(), actualCustomerDataFromDB.getEmail_id_alt());
	}

}
