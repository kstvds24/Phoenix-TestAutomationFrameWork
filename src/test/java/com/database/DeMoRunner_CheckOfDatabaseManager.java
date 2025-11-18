package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DeMoRunner_CheckOfDatabaseManager {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DataBaseManager.getConnection();
		System.out.println(conn);

	}

}
