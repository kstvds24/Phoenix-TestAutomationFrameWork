package com.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreaeJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DaoRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPyLoadData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for(CreateJobBean createJobBean: beanList)
		{
			CreateJobPayload payload = CreaeJobBeanMapper.mapper(createJobBean);
			payloadList.add(payload);
		}
		for(CreateJobPayload payload : payloadList)
		{
			System.out.println(payload);
		}

	}

}
