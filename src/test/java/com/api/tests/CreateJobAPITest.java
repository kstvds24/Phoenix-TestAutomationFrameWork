package com.api.tests;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobApiTest()
	{
		
		Customer customer = new Customer("Kaus", "Das", "7686923755", "", "kstvds@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("21", "Neer", "Kedar", "dumdum", "Sinthee", "700030", "India", "WB");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-02T18:30:00.000Z", "96548404098219", "96548404098219", "96548404098219", "2025-04-02T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		given()
		.spec(SpecUtil.RequestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("job/create")
		.then()
		.spec(SpecUtil.ResponseSpec_JSON(200))
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
	}

}
