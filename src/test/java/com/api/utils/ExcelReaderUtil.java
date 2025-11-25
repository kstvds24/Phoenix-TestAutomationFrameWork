package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtil {

	private ExcelReaderUtil() {

	}

	public static <T> Iterator<T> loadTestData(String xlsxFileName,String sheetName, Class<T> clazz) {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(xlsxFileName);  
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
		List<T> list = Poiji.fromExcel(mySheet, clazz);
		System.out.println(list);
		return list.iterator();

	}

}
