package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.JobHeadDBModel;

import io.qameta.allure.Step;

public class JobHeadDao {
	private static final Logger LOGGER = LogManager.getLogger(JobHeadDao.class);
	private static final String JOB_HEAD_QUERY = """
			Select * from tr_job_head where id=?
			""";

	private JobHeadDao() {

	}

	@Step("Retrieving Job Head Data From DataBase for specific id")
	public static JobHeadDBModel getJobHeadData(int customerId) {

		Connection conn;
		PreparedStatement preparedStatement;
		ResultSet rs;
		JobHeadDBModel jobHeadDBModel = null;
		try {
			LOGGER.info("Getting the connection from the database manager");
			conn = DataBaseManager.getConnection();
			LOGGER.info("Executing the SQL Query {}", JOB_HEAD_QUERY);
			preparedStatement = conn.prepareStatement(JOB_HEAD_QUERY);
			preparedStatement.setInt(1, customerId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				jobHeadDBModel = new JobHeadDBModel(rs.getInt("id"), rs.getString("job_number"),
						rs.getInt("tr_customer_id"), rs.getInt("tr_customer_product_id"),
						rs.getInt("mst_service_location_id"), rs.getInt("mst_platform_id"),
						rs.getInt("mst_warrenty_status_id"), rs.getInt("mst_oem_id"));
			}
		} catch (SQLException e) {
			LOGGER.error("Cannot Convert the dataset to bean {}", e);
			e.printStackTrace();
		}
		return jobHeadDBModel;
	}
}
