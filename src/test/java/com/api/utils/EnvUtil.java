package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {
	private static final Logger LOGGER = LogManager.getLogger(EnvUtil.class);
	private static Dotenv dotenv;
	private EnvUtil()
	{
		
	}
	static
	{
		LOGGER.info("Loading the .env file....");
		dotenv= Dotenv.load();
	}
	public static String getValue(String varName)
	{
		return dotenv.get(varName);
	}
}
