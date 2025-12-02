package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;
import com.database.model.ProblemsDBModel;

public class ProblemsDao {
	private static final String PROBLEMS_QUERY = """
			Select * from map_job_problem where tr_job_head_id=?
			""";
public ProblemsDao() {
}
public static ProblemsDBModel getProblemsDBInfo(int jobHeadId)
{
	ProblemsDBModel problemsDBData=null;
	Connection conn;
	PreparedStatement preparedStatement;
	ResultSet rs;
	try {
		 conn = DataBaseManager.getConnection();
		 preparedStatement = conn.prepareStatement(PROBLEMS_QUERY);
		 preparedStatement.setInt(1, jobHeadId);
		 rs= preparedStatement.executeQuery();
		 while(rs.next())
		 {
			 problemsDBData = new ProblemsDBModel(rs.getInt("mst_problem_id"), rs.getString("remark"));
		 }
		 
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return problemsDBData;
}
}
