package com.api.tests;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.SpecUtil;
import com.pojo.classes.CreateJobPayload;
import com.pojo.classes.Customer;
import com.pojo.classes.CustomerAddress;
import com.pojo.classes.CustomerProduct;
import com.pojo.classes.Problems;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobApiTest()
	{
		
		Customer customer = new Customer("Kaus", "Das", "7686923755", "", "kstvds@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("21", "Neer", "Kedar", "dumdum", "Sinthee", "700030", "India", "WB");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-02T18:30:00.000Z", "16548404098219", "16548404098219", "16548404098219", "2025-04-02T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		given()
		.spec(SpecUtil.RequestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("job/create")
		.then()
		.spec(SpecUtil.ResponseSpec_JSON(200));
	}

}
