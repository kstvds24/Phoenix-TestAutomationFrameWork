package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreaeJobBeanMapper;
import com.api.utils.FakeDataGenerator;
import com.api.utils.JsonReaderUtility;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProvidersUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel=true)
	public static Iterator<UserBean> loginDataProvider()
	{
		return CSVReaderUtil.loadCSV("TestData/LoginCreds.csv",UserBean.class);
	}
	@DataProvider(name = "LoginAPIJsonDataProvider", parallel=true)
	public static Iterator<UserCredentials> loginJsonDataProvider()
	{
		return JsonReaderUtility.loadJSON("TestData/LoginAPITestData.json",UserCredentials[].class);
	}
	@DataProvider(name = "CreateJobAPIDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobJsonDataProvider()
	{
		return JsonReaderUtility.loadJSON("TestData/CreateJobAPIData.json",CreateJobPayload[].class);
	}
	
	@DataProvider(name = "CreatejobAPIDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> CreateJobDataProvider()
	{
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("TestData/CreatejobData.csv", CreateJobBean.class);
		List<CreateJobPayload> createJobPayloadList =  new ArrayList<CreateJobPayload>();
		CreateJobBean createJobBeanTemp;
		CreateJobPayload createJobPayloadTemp;
		while(createJobBeanIterator.hasNext())
		{
			createJobBeanTemp = createJobBeanIterator.next();
			createJobPayloadTemp = CreaeJobBeanMapper.mapper(createJobBeanTemp);
			createJobPayloadList.add(createJobPayloadTemp);
			
		}
		return createJobPayloadList.iterator();
	}
	@DataProvider(name="CreteJobAPIFakerDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobFakeDataProvider()
	{
		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> payloadIterator = FakeDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;
	}
	@DataProvider(name="CreateJobAPIDBDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> CreateJobAPIDBDataProvider() {
		// TODO Auto-generated method stub
		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPyLoadData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for(CreateJobBean createJobBean: beanList)
		{
			CreateJobPayload payload = CreaeJobBeanMapper.mapper(createJobBean);
			payloadList.add(payload);
		}
		return payloadList.iterator();

	}
}
