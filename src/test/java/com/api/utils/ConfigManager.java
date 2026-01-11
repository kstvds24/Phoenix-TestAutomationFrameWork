package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.qameta.allure.Step;

public class ConfigManager {
	private static Properties prop = new Properties();
	private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);
	private static String path = "";
	public static String env;

	private ConfigManager() {

	}

	static {
		LOGGER.info("Reading env values passed from terminal");
		if (System.getProperty("env") == null) {
			LOGGER.warn("Env variable is not set....using qa as the env");
		}
		env = System.getProperty("env", "qa");
		LOGGER.info("Running the tests in the env {}", env);
		env = env.toLowerCase().trim();
		switch (env) {
		case "dev" -> path = "config/config.dev.properties";
		case "qa" -> path = "config/config.qa.properties";
		case "uat" -> path = "config/config.uat.properties";
		default -> path = "config/config.qa.properties";
		}
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (input == null) {
			LOGGER.error("Cannot find the file at the path {}" + path);
			throw new RuntimeException("config.properties file not found");

		}
		try {
			prop.load(input);
		} catch (IOException e) {
			LOGGER.error("Something went wrong with file at path {} {}" + path,e);
			e.printStackTrace();
		}

	}
	@Step("Getting the Property Value from the config file")
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
