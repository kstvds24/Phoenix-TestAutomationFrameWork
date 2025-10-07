package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader isr = new InputStreamReader(is);
		/*
		 * File file = new File(
		 * "C:\\Users\\kstvd\\eclipse-workspace\\PhoenixTestAutomationFramework\\src\\main\\resources\\TestData\\LoginCreds.csv"
		 * ); FileReader fr = new FileReader(file);
		 */
		CSVReader csvReader = new CSVReader(isr);
		
		List<String[]>data = csvReader.readAll();
		
		for(String[] dataArray : data)
		{
			for(String text: dataArray)
			{
				System.out.print(text + " ");
			}
			System.out.println();
		}

	}

}
