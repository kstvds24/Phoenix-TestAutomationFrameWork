package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {

	private static Dotenv dotenv;
	private EnvUtil()
	{
		
	}
	static
	{
		dotenv= Dotenv.load();
	}
	public static String getValue(String varName)
	{
		return dotenv.get(varName);
	}
}
