package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;
import com.database.model.JobHeadDBModel;

public class JobHeadDao {
private static final String JOB_HEAD_QUERY = """
		Select * from tr_job_head where tr_customer_id=?
		""";

private JobHeadDao()
{
	
}
public static JobHeadDBModel getJobHeadData(int customerId)
{
	Connection conn;
	PreparedStatement preparedStatement;
	ResultSet rs;
	JobHeadDBModel jobHeadDBModel = null;
	try {
		conn = DataBaseManager.getConnection();
		preparedStatement = conn.prepareStatement(JOB_HEAD_QUERY);
		preparedStatement.setInt(1, customerId);
		rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			jobHeadDBModel = new JobHeadDBModel(rs.getInt("id"), rs.getString("job_number"), 
					rs.getInt("tr_customer_id"), rs.getInt("tr_customer_product_id"), rs.getInt("mst_service_location_id"), 
					rs.getInt("mst_platform_id"), rs.getInt("mst_warrenty_status_id"), rs.getInt("mst_oem_id"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return jobHeadDBModel;
}
}
