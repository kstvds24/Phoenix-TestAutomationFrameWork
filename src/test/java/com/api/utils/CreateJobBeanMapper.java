package com.api.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.services.MasterService;
import com.dataproviders.api.bean.CreateJobBean;

import io.qameta.allure.Step;

public class CreateJobBeanMapper {
	private static final Logger LOGGER = LogManager.getLogger(CreateJobBeanMapper.class);
	private CreateJobBeanMapper()
	{
		
	}
	@Step("Converting the Create Job Bean to Create Job Payload for CreateJob API Test")
public static CreateJobPayload mapper(CreateJobBean bean)
{
	LOGGER.info("Converting the create job bean {} to create job payload",bean);
	int mstServiceLocationId =  Integer.parseInt(bean.getMst_service_location_id());
	int mstPlatformId =  Integer.parseInt(bean.getMst_platform_id());
	int mstWarrantyStatusId =  Integer.parseInt(bean.getMst_warrenty_status_id());
	int mstOemId =  Integer.parseInt(bean.getMst_oem_id());
	Customer customer = new Customer(bean.getCustomer__first_name(), 
			 bean.getCustomer__last_name(), 
			 bean.getCustomer__mobile_number(),
			 bean.getCustomer__mobile_number_alt(), 
			 bean.getCustomer__email_id(),
			 bean.getCustomer__email_id_alt());
	CustomerAddress customerAddress = new CustomerAddress(bean.getCustomer_address__flat_number(),
			 bean.getCustomer_address__apartment_name(), 
			 bean.getCustomer_address__street_name(), 
			 bean.getCustomer_address__landmark(), 
			 bean.getCustomer_address__area(), 
			 bean.getCustomer_address__pincode(), 
			 bean.getCustomer_address__country(), 
			 bean.getCustomer_address__state());
	CustomerProduct customerProduct = new CustomerProduct(bean.getCustomer_product__dop(), 
			bean.getCustomer_product__serial_number(), 
			bean.getCustomer_product__imei1(), 
			bean.getCustomer_product__imei2(),
			bean.getCustomer_product__popurl(),
			Integer.parseInt(bean.getCustomer_product__product_id()), 
			Integer.parseInt(bean.getCustomer_product__mst_model_id()));
	List<Problems> problemsList = new ArrayList<Problems>();
	Problems problems = new Problems(Integer.parseInt(bean.getProblems__id()), bean.getProblems__remark());
	problemsList.add(problems);
	CreateJobPayload payload = new CreateJobPayload(mstServiceLocationId, mstPlatformId, mstWarrantyStatusId, mstOemId, customer, customerAddress, customerProduct, problemsList);
	return payload;
}
}
