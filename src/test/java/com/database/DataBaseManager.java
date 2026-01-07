package com.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utils.ConfigManager;
import com.api.utils.EnvUtil;
import com.api.utils.VaultDBConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBaseManager {
	/*
	 * private static final String DB_URL = EnvUtil.getValue("DB_URL"); private
	 * static final String DB_USERNAME = EnvUtil.getValue("DB_USER_NAME"); private
	 * static final String DB_PASSWORD = EnvUtil.getValue("DB_PASSWORD");
	 */
	/*
	 * private static final String DB_URL = VaultDBConfig.getSecret("DB_URL");
	 * private static final String DB_USERNAME =
	 * VaultDBConfig.getSecret("DB_USER_NAME"); private static final String
	 * DB_PASSWORD = VaultDBConfig.getSecret("DB_PASSWORD");
	 */
	private static final Logger LOGGER = LogManager.getLogger(DataBaseManager.class);
	private static boolean isVaultUp = true;
	private static final String DB_URL = LoadSecrets("DB_URL");
	private static final String DB_USERNAME = LoadSecrets("DB_USER_NAME");
	private static final String DB_PASSWORD = LoadSecrets("DB_PASSWORD");
	private static final int MAXIMUM_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
	private static final int MINIMUM_IDLE_COUNT = Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));
	private static final int CONNECTION_TIMEOUT_IN_SECS = Integer
			.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));
	private static final int IDLE_TIMEOUT_IN_SECS = Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_IN_SECS"));
	private static final int MAX_LIFE_TIME_IN_MINS = Integer
			.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MINS"));
	private static final String HIKARI_CP_POOL_NAME = ConfigManager.getProperty("HIKARI_CP_POOL_NAME");
	private static Connection connection = null;
	private static HikariConfig hikariConfig;
	private volatile static HikariDataSource hikariDataSource;

	private static String LoadSecrets(String key) {
		String value = null;
		if (isVaultUp) {
			value = VaultDBConfig.getSecret(key);
			if (value == null) {
				LOGGER.error("Vault is Down!! or some issue with vault");
				isVaultUp = false;
			} else {
				LOGGER.info("READING VALUE FOR KEY {} FROM VAULT", key);
				return value;
			}
		}
		LOGGER.info("READING VALUE FOR KEY {} FROM ENV", key);
		value = EnvUtil.getValue(key);
		return value;
	}

	private static void instantiatePool() {
		if (hikariDataSource == null) {
			LOGGER.warn("DataBase Connection is not available---Creting HikariDataSource");
			synchronized (DataBaseManager.class) {
				if (hikariDataSource == null) {
					hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USERNAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS * 60 * 1000);
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);

					hikariDataSource = new HikariDataSource(hikariConfig);
					LOGGER.info("HikariDataSource created");
				}
			}

		}
	}

	public static Connection getConnection() throws SQLException {
		if (hikariDataSource == null) {
			LOGGER.info("Initializing the DataBase Connection using HikariCP");
			instantiatePool();
		} else if (hikariDataSource.isClosed()) {
			LOGGER.error("HIKARI Data Source is closed");
			throw new SQLException();
		}
		connection = hikariDataSource.getConnection();
		return connection;
	}

}
