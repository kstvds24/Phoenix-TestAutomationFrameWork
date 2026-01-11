package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnvironmentWriterUtility {
	private static final Logger LOGGER = LogManager.getLogger(AllureEnvironmentWriterUtility.class);
	public static void createEnvironmentPropertiesFile() {
		String folderPath = "target/allure-results";
		File file = new File(folderPath);
		file.mkdir();
		
		Properties prop= new Properties();
		prop.setProperty("Project Name", "Phoenix Test Automation FrameWork");
		prop.setProperty("Env", ConfigManager.env);
		prop.setProperty("BASE URI", ConfigManager.getProperty("BASE_URI"));
		prop.setProperty("Operating System", System.getProperty("os.name"));
		prop.setProperty("Operating System Version", System.getProperty("os.version"));
		prop.setProperty("Java Version", System.getProperty("java.version"));
		FileWriter fw;
		try {
			fw = new FileWriter(folderPath + "/environment.properties");
			prop.store(fw, "My Properties File");
			LOGGER.info("Created the environment.properties file at {}",folderPath);
		} catch (IOException e) {
			LOGGER.error("Unable to create the environment.properties file",e);
			e.printStackTrace();
		}
		
	}
}
