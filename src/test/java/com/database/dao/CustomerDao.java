package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {

	private static final String CUSTOMER_DETAILS_QUERY = """
			Select * from tr_customer WHERE id = ?
			""";
private CustomerDao()
{
	
}
	public static CustomerDBModel getCustomerInfo(int customerId) {
		CustomerDBModel customerDBModel = null;
		Connection conn;
		PreparedStatement preparedStatement;
		ResultSet result;
		try {
			conn = DataBaseManager.getConnection();
			preparedStatement = conn.prepareStatement(CUSTOMER_DETAILS_QUERY);
			preparedStatement.setInt(1, customerId);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				customerDBModel = new CustomerDBModel(result.getInt("id"), result.getString("first_name"), result.getString("last_name"),
						result.getString("mobile_number"), result.getString("mobile_number_alt"),
						result.getString("email_id"), result.getString("email_id_alt"),result.getInt("tr_customer_address_id"));
			}
		} catch (SQLException ex) {
			System.err.print(ex.getMessage());
		}
		return customerDBModel;
	}
}
