package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Step;

public class JsonReaderUtility {
	private static final Logger LOGGER = LogManager.getLogger(JsonReaderUtility.class);
	private JsonReaderUtility() {

	}
	@Step("Loading data from the json")
	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {
		LOGGER.info("Reading the json from the file {}",fileName);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		T[] classArray;
		List<T> list = null;

		try {
			LOGGER.info("Converting the json to the bean class {}",clazz);
			classArray = objectMapper.readValue(is, clazz);
			list = Arrays.asList(classArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.iterator();
	}
}
