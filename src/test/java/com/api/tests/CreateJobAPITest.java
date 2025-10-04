package com.api.tests;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.Oem;
import com.api.constants.Platfrom;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.ServiceLocation;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobApiTest()
	{
		
		Customer customer = new Customer("Kaus", "Das", "7686923755", "", "kstvds@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("21", "Neer", "Kedar", "dumdum", "Sinthee", "700030", "India", "WB");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "96548404098211", "96548404098211", "96548404098211", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platfrom.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), Oem.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
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
