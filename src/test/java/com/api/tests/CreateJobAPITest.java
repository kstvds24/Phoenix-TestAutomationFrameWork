package com.api.tests;
import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.ResponseSpec_JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
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
import com.api.services.JobService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.APITestListeners.class)
@Epic("Job Management")
@Feature("Job Creation")
public class CreateJobAPITest {
	CreateJobPayload createJobPayload;
	
	@BeforeMethod(description = "Creating the create job payload request")
	public void setup()
	{
		Customer customer = new Customer("Kaus", "Das", "7686923755", "", "kstvds@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("21", "Neer", "Kedar", "dumdum", "Sinthee", "700030", "India", "WB");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "96548404198211", "96548404198211", "96548404198211", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platfrom.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), Oem.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
	}
	@Story("FD should be able to create job")
	@Description("Verify if Create Job API is able to create in-warranty jobs for FD User")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description="Verify if Create Job API is able to create in-warranty jobs",groups= {"api","regression","smoke"})
	public void createJobApiTest()
	{
		JobService.createJob(Role.FD, createJobPayload)
		.then()
		.spec(ResponseSpec_JSON(200))
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
	}

}
