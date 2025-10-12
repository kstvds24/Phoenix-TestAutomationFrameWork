package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.bean.*;

public class ReadCSVFile_MapToPOJO2 {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);
		
		CsvToBean<UserPOJO> csvToBean = new CsvToBeanBuilder(csvReader).withType(UserPOJO.class).withIgnoreEmptyLine(true).build();
		
		List<UserPOJO> userList = csvToBean.parse();
		System.out.println(userList);
		System.out.println(userList.get(1).getUsername());

	}

}
