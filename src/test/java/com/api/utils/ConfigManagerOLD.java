package com.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
	private static ConfigManagerOLD instance;
	private static Properties prop = new Properties();

	private ConfigManagerOLD() {
		File myFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties");
		FileReader fr;
		try {
			fr = new FileReader(myFile);
			prop.load(fr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ConfigManagerOLD getInstance() {
		if (instance == null) {
			synchronized (ConfigManagerOLD.class) {
				if (instance == null) {
					instance = new ConfigManagerOLD();
				}
			}
		}
		return instance;
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}
