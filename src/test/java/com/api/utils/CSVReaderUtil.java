package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	/*
	 * 
	 * Constructor is Private
	 * 
	 * sattic method job is to help read the CSV file and map it to bean
	 * 
	 */
	private CSVReaderUtil() {

	}

	public static void loadCSV(String pathOfCSVFile) {
		// TODO Auto-generated method stub
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);

		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(isr)
				.withType(UserBean.class).
				withIgnoreEmptyLine(true)
				.build();

		List<UserBean> list = csvToBean.parse();
		System.out.println(list);
	}
}
