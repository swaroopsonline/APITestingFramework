package com.swa.APITestingFramework.utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swa.APITestingFramework.setUp.BaseTest;
//import com.swa.utilities.Constants;
import com.swa.APITestingFramework.utilities.*;

public class DataUtil extends BaseTest{
	

	
	/*
	 * @Test(dataProvider="getData") public void testData(Hashtable<String, String>
	 * data) {
	 * 
	 * //System.out.println(data.get("Runmode")+"---"+data.get("firstname")+"---"+
	 * data.get("lastname")+"---"+data.get("postcode")); }
	 */

	@DataProvider(name="data")
	public static Object[][] getData(Method m) {

//		ExcelReader excel = new ExcelReader(
//				System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\BankManagerSuite.xlsx");

		int rows = excel.getRowCount(config.getProperty("testDataSheet"));
		System.out.println("Total rows are : " + rows);

		//String testName = "OpenAccountTest";
		String testName = m.getName();
		System.out.println("Test name is : "+testName);
		
		// Find the test case start row

		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(config.getProperty("testDataSheet"), 0, testCaseRowNum);

			if (testCaseName.equalsIgnoreCase(testName))
				break;

		}

		System.out.println("Test case starts from row num: " + testCaseRowNum);

		// Checking total rows in test case

		int dataStartRowNum = testCaseRowNum + 2;

		int testRows = 0;
		while (!excel.getCellData(config.getProperty("testDataSheet"), 0, dataStartRowNum + testRows).equals("")) {

			testRows++;
		}

		System.out.println("Total rows of data are : " + testRows);

		// Checking total cols in test case

		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;
		while (!excel.getCellData(config.getProperty("testDataSheet"), testCols, colStartColNum).equals("")) {

			testCols++;
		}

		System.out.println("Total cols are : " + testCols);

		// Printing data
		
		Object[][] data =  new Object [testRows][1];

		int i=0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {
			
			Hashtable<String, String> table = new Hashtable<String, String>();
			
			for (int cNum = 0; cNum < testCols; cNum++) {

				//System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
				//data[rNum-dataStartRowNum][cNum]=excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
				String testData = excel.getCellData(config.getProperty("testDataSheet"), cNum, rNum);
				String colName = excel.getCellData(config.getProperty("testDataSheet"), cNum, colStartColNum);
				
				table.put(colName, testData);
				
			}
			
			data[i][0] = table;
			i++;
			
			
			
		}
		return data;
	}


}
