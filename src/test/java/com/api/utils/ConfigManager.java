package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	//private static ConfigManager2 instance;
	private static Properties prop = new Properties();
	private static String path = "";
	private static String env;
	private ConfigManager()
	{
		
	}

	static {
		env = System.getProperty("env","qa");
		env = env.toLowerCase().trim();
		switch(env)
		{
		case "dev" -> path = "config/config.dev.properties";
		case "qa" -> path = "config/config.qa.properties";
		case "uat" -> path = "config/config.uat.properties";
		default  -> path = "config/config.qa.properties";
		}
		InputStream input =  Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if(input == null)
		{
			throw new RuntimeException("config.properties file not found");
		}
			try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
