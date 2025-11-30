package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.RequestSpecWithAuth;
import static com.api.utils.SpecUtil.ResponseSpec_JSON;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.JobHeadDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.JobHeadDBModel;

import io.restassured.response.Response;

public class CreateJobAPIWithDBValidationTest {
	CreateJobPayload createJobPayload;
	Customer customer;
	CustomerAddress customerAddress;
	CustomerProduct customerProduct;

	@BeforeMethod
	public void setup() {
		customer = new Customer("Kaus", "Das", "7686923755", "", "kstvds@gmail.com", "");
		customerAddress = new CustomerAddress("21", "Neer", "Kedar", "dumdum", "Sinthee", "700030", "India", "WB");
		customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "99548501098998",
				"99548501098998", "99548501098998", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(),
				Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
				Platfrom.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), Oem.GOOGLE.getCode(), customer,
				customerAddress, customerProduct, problemsList);
	}

	@Test(description = "Verify if Create Job API is able to create in-warranty jobs", groups = { "api", "regression",
			"smoke" })
	public void createJobApiTest() {
		Response response = given().spec(RequestSpecWithAuth(Role.FD, createJobPayload)).when().post("job/create").then()
				.spec(ResponseSpec_JSON(200))
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_")).
				extract().response();
		int customerId = response.then().extract().jsonPath().getInt("data.tr_customer_id");
		CustomerDBModel customerDataFromDB = CustomerDao.getCustomerInfo(customerId);
		Assert.assertEquals(customer.first_name(), customerDataFromDB.getFirst_name());
		Assert.assertEquals(customer.last_name(), customerDataFromDB.getLast_name());
		Assert.assertEquals(customer.mobile_number(), customerDataFromDB.getMobile_number());
		Assert.assertEquals(customer.mobile_number_alt(), customerDataFromDB.getMobile_number_alt());
		Assert.assertEquals(customer.email_id(), customerDataFromDB.getEmail_id());
		Assert.assertEquals(customer.email_id_alt(), customerDataFromDB.getEmail_id_alt());
		CustomerAddressDBModel customerAddressDataFromDB = CustomerAddressDao
				.getCustomerAddressData(customerDataFromDB.getTr_customer_address_id());
		Assert.assertEquals(customerAddress.flat_number(), customerAddressDataFromDB.getFlat_number());
		Assert.assertEquals(customerAddress.apartment_name(),
				customerAddressDataFromDB.getApartment_name());
		Assert.assertEquals(customerAddress.street_name(), customerAddressDataFromDB.getStreet_name());
		Assert.assertEquals(customerAddress.landmark(), customerAddressDataFromDB.getLandmark());
		Assert.assertEquals(customerAddress.area(), customerAddressDataFromDB.getArea());
		Assert.assertEquals(customerAddress.pincode(), customerAddressDataFromDB.getPincode());
		Assert.assertEquals(customerAddress.state(), customerAddressDataFromDB.getState());
		Assert.assertEquals(customerAddress.country(), customerAddressDataFromDB.getCountry());
		int customerProductId = response.then().extract().body().jsonPath().getInt("data.tr_customer_product_id");
		CustomerProductDBModel customerProductDataFromDb = CustomerProductDao.getCustomerProductId(customerProductId);
		Assert.assertEquals(customerProduct.mst_model_id(), customerProductDataFromDb.getMst_model_id());
		Assert.assertEquals(customerProduct.dop(), customerProductDataFromDb.getDop());
		Assert.assertEquals(customerProduct.popurl(), customerProductDataFromDb.getPopurl());
		Assert.assertEquals(customerProduct.imei1(), customerProductDataFromDb.getImei1());
		Assert.assertEquals(customerProduct.imei2(), customerProductDataFromDb.getImei2());
		Assert.assertEquals(customerProduct.serial_number(), customerProductDataFromDb.getSerial_number());
		JobHeadDBModel jobHeadDBData = JobHeadDao.getJobHeadData(customerId);
		Assert.assertEquals(createJobPayload.mst_oem_id(), jobHeadDBData.getMst_oem_id());
		Assert.assertEquals(createJobPayload.mst_platform_id(), jobHeadDBData.getMst_platform_id());
		Assert.assertEquals(createJobPayload.mst_service_location_id(),
				jobHeadDBData.getMst_service_location_id());
		Assert.assertEquals(createJobPayload.mst_warrenty_status_id(),
				jobHeadDBData.getMst_warrenty_status_id());
		Assert.assertTrue(jobHeadDBData.getJob_number().startsWith("JOB_"));
	}

}
