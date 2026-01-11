package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.CustomerAddressDBModel;

import io.qameta.allure.Step;

public class CustomerAddressDao {

	private static final Logger LOGGER = LogManager.getLogger(CustomerAddressDao.class);
	private static final String CUSTOMER_ADDRESS_QUERY = """
			Select * from tr_customer_address where id=?
			""";

	private CustomerAddressDao() {

	}

	@Step("Retrieving Customer Address Data From DataBase for specific id")
	public static CustomerAddressDBModel getCustomerAddressData(int customerAddressId) {
		Connection conn;
		PreparedStatement preparedStatement;
		ResultSet rs;
		CustomerAddressDBModel customerAddressDBModel = null;
		try {
			LOGGER.info("Getting the connection from the database manager");
			conn = DataBaseManager.getConnection();
			LOGGER.info("Executing the SQL Query {}", CUSTOMER_ADDRESS_QUERY);
			preparedStatement = conn.prepareStatement(CUSTOMER_ADDRESS_QUERY);
			preparedStatement.setInt(1, customerAddressId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				customerAddressDBModel = new CustomerAddressDBModel(rs.getInt("id"), rs.getString("flat_number"),
						rs.getString("apartment_name"), rs.getString("street_name"), rs.getString("landmark"),
						rs.getString("area"), rs.getString("pincode"), rs.getString("country"), rs.getString("state"));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot Convert the dataset to bean {}", e);
			e.printStackTrace();
		}
		return customerAddressDBModel;
	}
}
