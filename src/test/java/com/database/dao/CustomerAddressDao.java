package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;
import com.database.model.CustomerAddressDBModel;

public class CustomerAddressDao {

	private static final String CUSTOMER_ADDRESS_QUERY = """
			Select * from tr_customer_address where id=?
			""";

	private CustomerAddressDao() {

	}

	public static CustomerAddressDBModel getCustomerAddressData(int customerAddressId) {
		Connection conn;
		PreparedStatement preparedStatement;
		ResultSet rs;
		CustomerAddressDBModel customerAddressDBModel = null;
		try {
			conn = DataBaseManager.getConnection();
			preparedStatement = conn.prepareStatement(CUSTOMER_ADDRESS_QUERY);
			preparedStatement.setInt(1, customerAddressId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				customerAddressDBModel = new CustomerAddressDBModel(rs.getInt("id"), rs.getString("flat_number"),
						rs.getString("apartment_name"), rs.getString("street_name"), rs.getString("landmark"),
						rs.getString("area"), rs.getString("pincode"), rs.getString("country"), rs.getString("state"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerAddressDBModel;
	}
}
