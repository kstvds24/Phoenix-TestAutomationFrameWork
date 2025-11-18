package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDemo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariConfig.setUsername(ConfigManager.getProperty("DB_USER_NAME"));
		hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setConnectionTimeout(10000);
		hikariConfig.setIdleTimeout(10000);
		hikariConfig.setMaxLifetime(1800000);
		hikariConfig.setPoolName("Pheonix Test Automation  Framework");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		Connection conn = hikariDataSource.getConnection();
		System.out.println(conn);
		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("Select first_name,last_name,mobile_number from tr_customer;");
		 while(rs.next())
	        {
	        	String first_name = rs.getString("first_name");
	        	String last_name = rs.getString("last_name");
	        	String mobile_number = rs.getString("mobile_number");
	        	System.out.println(first_name + "|" + last_name +"|" + mobile_number);
	        }
		

	}

}
