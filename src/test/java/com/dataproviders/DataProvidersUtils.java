package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakeDataGenerator;
import com.api.utils.JsonReaderUtility;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProvidersUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginDataProvider() {
		return CSVReaderUtil.loadCSV("TestData/LoginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public static Iterator<UserCredentials> loginJsonDataProvider() {
		return JsonReaderUtility.loadJSON("TestData/LoginAPITestData.json", UserCredentials[].class);
	}

	@DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
	public static Iterator<UserBean> loginExcelDataProvider() {
		return ExcelReaderUtil.loadTestData("TestData/PhoenixTestData.xlsx","LoginTestData",UserBean.class);
	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobJsonDataProvider() {
		return JsonReaderUtility.loadJSON("TestData/CreateJobAPIData.json", CreateJobPayload[].class);
	}

	@DataProvider(name = "CreatejobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobDataProvider() {
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("TestData/CreatejobData.csv",
				CreateJobBean.class);
		List<CreateJobPayload> createJobPayloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean createJobBeanTemp;
		CreateJobPayload createJobPayloadTemp;
		while (createJobBeanIterator.hasNext()) {
			createJobBeanTemp = createJobBeanIterator.next();
			createJobPayloadTemp = CreateJobBeanMapper.mapper(createJobBeanTemp);
			createJobPayloadList.add(createJobPayloadTemp);

		}
		return createJobPayloadList.iterator();
	}
	@DataProvider(name = "CreatejobAPIExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobExcelDataProvider() {
		Iterator<CreateJobBean> createJobBeanIterator = ExcelReaderUtil.loadTestData("TestData/PhoenixTestData.xlsx","CreateJobTestData", CreateJobBean.class);
		List<CreateJobPayload> createJobPayloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean createJobBeanTemp;
		CreateJobPayload createJobPayloadTemp;
		while (createJobBeanIterator.hasNext()) {
			createJobBeanTemp = createJobBeanIterator.next();
			createJobPayloadTemp = CreateJobBeanMapper.mapper(createJobBeanTemp);
			createJobPayloadList.add(createJobPayloadTemp);

		}
		return createJobPayloadList.iterator();
	}

	@DataProvider(name = "CreteJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobFakeDataProvider() {
		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> payloadIterator = FakeDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;
	}
}
