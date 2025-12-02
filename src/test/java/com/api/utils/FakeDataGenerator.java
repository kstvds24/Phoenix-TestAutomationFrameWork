package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakeDataGenerator {

	private static Faker faker = new Faker(new Locale("en-IND"));
	private static final String COUNTRY = "India";
	private static Random random = new Random();
	private static final int MST_SERVICE_LOCATION_ID = 1;
	private static final int MST_PLATFORM_ID = 2;
	private static final int MST_WARRANTY_STATUS_ID = 1;
	private static final int MST_OEM_ID = 1;
	private static final int PRODUCT_ID = 1;
	private static final int MST_MODEL_ID = 1;
	private static final int[] VALIDPROBLEMID = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24, 26,
			27, 28, 29 };

	private FakeDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeProblemList();
		return new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID,
				customer, customerAddress, customerProduct, problemList);
	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for (int i = 0; i < count; i++) {

			Customer customer = generateFakeCustomerData();
			CustomerAddress customerAddress = generateFakeCustomerAddressData();
			CustomerProduct customerProduct = generateFakeCustomerProductData();
			List<Problems> problemList = generateFakeProblemList();
			CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
			payloadList.add(createJobPayload);
		}
		return payloadList.iterator();
	}

	private static List<Problems> generateFakeProblemList() {
		int count = random.nextInt(3) + 1;
		List<Problems> problemList = new ArrayList<Problems>();
		String fakeRemark;
		int randomIndex;
		Problems problems;
		for (int i = 0; i < count; i++) {
			fakeRemark = faker.lorem().sentence(4);

			randomIndex = random.nextInt(VALIDPROBLEMID.length);
			problems = new Problems(VALIDPROBLEMID[randomIndex], fakeRemark);
			problemList.add(problems);
		}
		return problemList;
	}

	private static CustomerProduct generateFakeCustomerProductData() {
		String dob = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();
		CustomerProduct customerProduct = new CustomerProduct(dob, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, PRODUCT_ID, MST_MODEL_ID);
		return customerProduct;
	}

	private static CustomerAddress generateFakeCustomerAddressData() {
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pincode, COUNTRY, state);
		return customerAddress;
	}

	private static Customer generateFakeCustomerData() {
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String customerMobileNumber = faker.numerify("701#######");
		String customerAltMobileNumber = faker.numerify("701#######");
		String customerEmailId = faker.internet().emailAddress();
		String customerAltEmailId = faker.internet().emailAddress();
		Customer customer = new Customer(fname, lname, customerMobileNumber, customerAltMobileNumber, customerEmailId,
				customerAltEmailId);
		return customer;
	}
}
