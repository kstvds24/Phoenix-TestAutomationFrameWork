package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {
	private static final String CUSTOMER_PROUCT_QUERY=
			"""
			Select * from tr_customer_product where id =?
			""";
	public static CustomerProductDBModel getCustomerProductId(int trCustomerId)
	{
		CustomerProductDBModel customerProductData = null;
		Connection conn;
		ResultSet rs;
		PreparedStatement preparedStatement;
		try {
			conn = DataBaseManager.getConnection();
			 preparedStatement = conn.prepareStatement(CUSTOMER_PROUCT_QUERY);
			 preparedStatement.setInt(1, trCustomerId);
			 rs = preparedStatement.executeQuery();
			 while(rs.next())
			 {
				 customerProductData = new CustomerProductDBModel(rs.getInt("tr_customer_id"), 
						 rs.getInt("mst_model_id"), rs.getString("dop"), rs.getString("popurl"), 
						 rs.getString("imei2"), rs.getString("imei1"), rs.getString("serial_number"));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerProductData;
	}

}
