package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtility {

	private JsonReaderUtility() {

	}

	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/demo.json");
		ObjectMapper objectMapper = new ObjectMapper();
		T[] classArray;
		List<T> list = null;

		try {
			classArray = objectMapper.readValue(is, clazz);
			list = Arrays.asList(classArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.iterator();
	}
}
