package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;

public class ExcelReaderUtil {

	private ExcelReaderUtil() {

	}

	public static Iterator<UserCredentials> loadTestData() {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("TestData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet mySheet = myWorkBook.getSheet("LoginTestData");

		XSSFRow headerRows = mySheet.getRow(0);
		int userNameIndex = -1;
		int passwordIndex = -1;
		for (Cell cell : headerRows) {
			if (cell.getStringCellValue().trim().equals("Username"))
				userNameIndex = cell.getColumnIndex();
			if (cell.getStringCellValue().trim().equals("Password"))
				passwordIndex = cell.getColumnIndex();
		}
		int lastRowIndex = mySheet.getLastRowNum();
		XSSFRow rowData;
		ArrayList<UserCredentials> userList = new ArrayList<UserCredentials>();
		UserCredentials userCredentials;
		for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
			rowData = mySheet.getRow(rowIndex);
			userCredentials = new UserCredentials(rowData.getCell(userNameIndex).toString(),
					rowData.getCell(passwordIndex).toString());
			userList.add(userCredentials);

		}
		return userList.iterator();

	}

}
