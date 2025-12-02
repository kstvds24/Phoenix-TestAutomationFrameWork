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
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.JobHeadDao;
import com.database.dao.ProblemsDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.JobHeadDBModel;
import com.database.model.ProblemsDBModel;

import io.restassured.response.Response;

public class CreateJobAPITestWithFakeData {
	CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Creating create job api payload")
	public void setup() {
		createJobPayload = FakeDataGenerator.generateFakeCreateJobData();
		System.out.println(createJobPayload);
	}

	@Test(description = "Verify if Create Job API is able to create in-warranty jobs", groups = { "api", "regression",
			"smoke" })
	public void createJobApiTest() {
		Response response = given().spec(RequestSpecWithAuth(Role.FD, createJobPayload)).when().post("job/create")
				.then().spec(ResponseSpec_JSON(200))
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_")).extract().response();
		int customerId = response.body().jsonPath().getInt("data.tr_customer_id");
		CustomerDBModel actualCustomerDataFromDB = CustomerDao.getCustomerInfo(customerId);
		Customer expectedCustomerData = createJobPayload.customer();
		Assert.assertEquals(expectedCustomerData.first_name(), actualCustomerDataFromDB.getFirst_name());
		Assert.assertEquals(expectedCustomerData.last_name(), actualCustomerDataFromDB.getLast_name());
		Assert.assertEquals(expectedCustomerData.mobile_number(), actualCustomerDataFromDB.getMobile_number());
		Assert.assertEquals(expectedCustomerData.mobile_number_alt(), actualCustomerDataFromDB.getMobile_number_alt());
		Assert.assertEquals(expectedCustomerData.email_id(), actualCustomerDataFromDB.getEmail_id());
		Assert.assertEquals(expectedCustomerData.email_id_alt(), actualCustomerDataFromDB.getEmail_id_alt());
		CustomerAddressDBModel customerAddressDataFromDB = CustomerAddressDao
				.getCustomerAddressData(actualCustomerDataFromDB.getTr_customer_address_id());
		Assert.assertEquals(createJobPayload.customer_address().flat_number(),
				customerAddressDataFromDB.getFlat_number());
		Assert.assertEquals(createJobPayload.customer_address().apartment_name(),
				customerAddressDataFromDB.getApartment_name());
		Assert.assertEquals(createJobPayload.customer_address().street_name(),
				customerAddressDataFromDB.getStreet_name());
		Assert.assertEquals(createJobPayload.customer_address().landmark(), customerAddressDataFromDB.getLandmark());
		Assert.assertEquals(createJobPayload.customer_address().area(), customerAddressDataFromDB.getArea());
		Assert.assertEquals(createJobPayload.customer_address().pincode(), customerAddressDataFromDB.getPincode());
		Assert.assertEquals(createJobPayload.customer_address().state(), customerAddressDataFromDB.getState());
		Assert.assertEquals(createJobPayload.customer_address().country(), customerAddressDataFromDB.getCountry());
		int customerProductId = response.then().extract().body().jsonPath().getInt("data.tr_customer_product_id");
		CustomerProductDBModel customerProductDataFromDb = CustomerProductDao.getCustomerProductId(customerProductId);
		Assert.assertEquals(createJobPayload.customer_product().mst_model_id(),
				customerProductDataFromDb.getMst_model_id());
		Assert.assertEquals(createJobPayload.customer_product().imei1(), customerProductDataFromDb.getImei1());
		Assert.assertEquals(createJobPayload.customer_product().imei2(), customerProductDataFromDb.getImei2());
		Assert.assertEquals(createJobPayload.customer_product().popurl(), customerProductDataFromDb.getPopurl());
		Assert.assertEquals(createJobPayload.customer_product().serial_number(),
				customerProductDataFromDb.getSerial_number());
		int id = response.then().extract().body().jsonPath().getInt("data.id");
		JobHeadDBModel jobHeadDBData = JobHeadDao.getJobHeadData(id);
		Assert.assertEquals(createJobPayload.mst_oem_id(), jobHeadDBData.getMst_oem_id());
		Assert.assertEquals(createJobPayload.mst_platform_id(), jobHeadDBData.getMst_platform_id());
		Assert.assertEquals(createJobPayload.mst_service_location_id(), jobHeadDBData.getMst_service_location_id());
		Assert.assertEquals(createJobPayload.mst_warrenty_status_id(), jobHeadDBData.getMst_warrenty_status_id());
		Assert.assertTrue(jobHeadDBData.getJob_number().startsWith("JOB_"));

	}

}
