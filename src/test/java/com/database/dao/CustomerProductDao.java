package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.CustomerProductDBModel;

import io.qameta.allure.Step;

public class CustomerProductDao {
	private static final Logger LOGGER = LogManager.getLogger(CustomerProductDao.class);
	private static final String CUSTOMER_PROUCT_QUERY = """
			Select * from tr_customer_product where id =?
			""";

	private CustomerProductDao() {

	}

	@Step("Retrieving Customer Product Data From DataBase for specific id")
	public static CustomerProductDBModel getCustomerProductId(int trCustomerId) {
		CustomerProductDBModel customerProductData = null;
		Connection conn;
		ResultSet rs;
		PreparedStatement preparedStatement;
		try {
			LOGGER.info("Getting the connection from the database manager");
			conn = DataBaseManager.getConnection();
			LOGGER.info("Executing the SQL Query {}", CUSTOMER_PROUCT_QUERY);
			preparedStatement = conn.prepareStatement(CUSTOMER_PROUCT_QUERY);
			preparedStatement.setInt(1, trCustomerId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				customerProductData = new CustomerProductDBModel(rs.getInt("tr_customer_id"), rs.getInt("mst_model_id"),
						rs.getString("dop"), rs.getString("popurl"), rs.getString("imei2"), rs.getString("imei1"),
						rs.getString("serial_number"));
			}
		} catch (SQLException e) {
			LOGGER.error("Cannot Convert the dataset to bean {}", e);
			e.printStackTrace();
		}
		return customerProductData;
	}

}
