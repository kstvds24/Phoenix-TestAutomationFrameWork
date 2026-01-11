package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.ProblemsDBModel;

import io.qameta.allure.Step;

public class ProblemsDao {
	private static final Logger LOGGER = LogManager.getLogger(ProblemsDao.class);
	private static final String PROBLEMS_QUERY = """
			Select * from map_job_problem where tr_job_head_id=?
			""";

	public ProblemsDao() {
	}

	@Step("Retrieving Customer Problems Data From DataBase for specific id")
	public static ProblemsDBModel getProblemsDBInfo(int jobHeadId) {
		ProblemsDBModel problemsDBData = null;
		Connection conn;
		PreparedStatement preparedStatement;
		ResultSet rs;
		try {
			LOGGER.info("Getting the connection from the database manager");
			conn = DataBaseManager.getConnection();
			LOGGER.info("Executing the SQL Query {}", PROBLEMS_QUERY);
			preparedStatement = conn.prepareStatement(PROBLEMS_QUERY);
			preparedStatement.setInt(1, jobHeadId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				problemsDBData = new ProblemsDBModel(rs.getInt("mst_problem_id"), rs.getString("remark"));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot Convert the dataset to bean {}", e);
			e.printStackTrace();
		}
		return problemsDBData;
	}
}
