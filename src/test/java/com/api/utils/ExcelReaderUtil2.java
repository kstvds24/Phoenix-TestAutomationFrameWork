package com.api.utils;


import java.util.Iterator;

import com.api.request.model.CreateJobPayload;
import com.dataproviders.api.bean.CreateJobBean;

public class ExcelReaderUtil2 {

	public static void main(String[] args) {
		Iterator<CreateJobBean> iterator= ExcelReaderUtil.loadTestData("TestData/PhoenixTestData.xlsx","CreateJobTestData", CreateJobBean.class);
		
	while(iterator.hasNext())
	{
		CreateJobBean createJobBean = iterator.next();
		CreateJobPayload createJobPayload = CreateJobBeanMapper.mapper(createJobBean);
		System.out.println(createJobPayload);
	}
	
	}

}
