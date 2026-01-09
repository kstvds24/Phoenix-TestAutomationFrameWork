package com.api.tests;

import static com.api.constants.Role.FD;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.SearchJob;
import com.api.services.JobService;
import com.api.utils.SpecUtil;
@Listeners(com.listeners.APITestListeners.class)
public class SearchAPITest {
	private SearchJob searchJobPayload;
	private static final String jobNumber = "JOB_134123";
	@BeforeTest
	public void initialize()
	{
		searchJobPayload = new SearchJob(jobNumber);
	}
	@Test(description = "Verify if Job Search API is working fine", groups = {"api", "regression","e2e"})
	public void detailsAPITest()
	{
		JobService.searchJob(FD, searchJobPayload)
		.then()
		.spec(SpecUtil.ResponseSpec_JSON(200))
		.body("message", equalTo("Success"));
	}

}
