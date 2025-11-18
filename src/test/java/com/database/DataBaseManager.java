package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;

public class DataBaseManager {
	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USERNAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	public volatile static Connection conn;

	public synchronized void createConnection() throws SQLException {
		if (conn == null) {
			synchronized (DataBaseManager.class) {
				if(conn == null)
				{
					conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				}
			}
			
		}
	}

}
