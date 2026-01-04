package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakeDataGenerator;
import com.api.utils.JsonReaderUtility;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProvidersUtils {
	private static final Logger LOGGER = LogManager.getLogger(DataProvidersUtils.class);

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginDataProvider() {
		LOGGER.info("Loading Data from the CSV file TestData/LoginCreds.csv");
		return CSVReaderUtil.loadCSV("TestData/LoginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public static Iterator<UserBean> loginJsonDataProvider() {
		LOGGER.info("Loading Data from the JSON file TestData/LoginAPITestData.json");
		return JsonReaderUtility.loadJSON("TestData/LoginAPITestData.json", UserBean[].class);
	}

	@DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
	public static Iterator<UserBean> loginExcelDataProvider() {
		LOGGER.info("Loading Data from the EXCEL file TestData/PhoenixTestData.xlsx");
		return ExcelReaderUtil.loadTestData("TestData/PhoenixTestData.xlsx", "LoginTestData", UserBean.class);
	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobJsonDataProvider() {
		LOGGER.info("Loading Data from the JSON file TestData/CreateJobAPIData.json");
		return JsonReaderUtility.loadJSON("TestData/CreateJobAPIData.json", CreateJobPayload[].class);
	}

	@DataProvider(name = "CreatejobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobDataProvider() {
		LOGGER.info("Loading Data from the CSV file TestData/CreatejobData.csv");
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
		LOGGER.info("Loading Data from the Excel file TestData/PhoenixTestData.xlsx");
		Iterator<CreateJobBean> createJobBeanIterator = ExcelReaderUtil.loadTestData("TestData/PhoenixTestData.xlsx",
				"CreateJobTestData", CreateJobBean.class);
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
		LOGGER.info("Creating fake job data with count {}", fakerCount);
		int fakerCountInt = Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> payloadIterator = FakeDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;
	}

	@DataProvider(name = "CreateJobAPIDBDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIDBDataProvider() {
		LOGGER.info("Loading Data from DataBase for Create Job Payload");
		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPyLoadData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for (CreateJobBean createJobBean : beanList) {
			CreateJobPayload payload = CreateJobBeanMapper.mapper(createJobBean);
			payloadList.add(payload);
		}
		return payloadList.iterator();

	}
}
