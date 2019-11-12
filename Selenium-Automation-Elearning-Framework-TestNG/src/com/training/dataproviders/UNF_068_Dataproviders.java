package com.training.dataproviders;


import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.training.utility.ExcelUtils;

//dataProvider for UNF_068 testcase
public class UNF_068_Dataproviders {

	    //declaring an XSSFSheet variable to store sheet in
		static XSSFSheet sheet;
		
		//declaring an XSSFWorkbook variable to workbook sheet in
		static XSSFWorkbook workbook;
		
		//varibale which will be used an instance of ExcelUtils class
		static ExcelUtils excel;
		
		//the excel file path within the project folder and the sheetname that contains the testdata
		private String filePath = "/ExcelWorkbooks/Uniform_Test Cases_Complex.xlsx";
		private String sheetName = "TestData";
	    
		//the data provider with name UNFTD_007. used in UNF_068 testcase by giving the same name
		@DataProvider(name="UNFTD_007")
		public  Object[][] getData() throws IOException {
			
			//to get the project location so that we dont have to hardcode filepath
			//this gives, in our case: projectPath = C:\Users\VivekPunia\git\UniformSeleniumProject\Selenium-Automation-Elearning-Framework-TestNG
			String projectPath = System.getProperty("user.dir");
		
			//workbook at location: projectPath + /ExcelWorkbooks/Uniform_Test Cases_Complex.xlsx , which is the location of our excel file
			workbook = new XSSFWorkbook(projectPath + filePath);
			
			//the sheet that contains our test data is names sheetName = "TestData"
			sheet = workbook.getSheet(sheetName);
			
			//creating an instance of ExcelUtils. This class contains the general excel utility functions
			//like getting rowCount, getting cell data etc...
			excel =new ExcelUtils(filePath, sheetName);
			
			//the rows and columns which contains the test data relevant to this test case
	        int startingRow = 44;
	        int endingRow = 47;
	        int startingCol = 2 ;
	        int endingCol = 6;
			
	        //an object that will contain out data from excel sheet
			Object[][] data = new Object[endingRow - startingRow + 1 ][endingCol - startingCol + 1];
			
			//iterating through rows and columns in the excel that we want
			for(int i= 0; i<endingRow - startingRow + 1; i++) {
				for(int j = 0; j<endingCol - startingCol + 1; j++) {
					if(j==3) {
						//this column contains numeric value, hence used getCellDataNumeric separately
						data[i][j] = excel.getCellDataNumeric((startingRow + i), (startingCol + j));
					}
					else
					data[i][j] = excel.getCellDataString(startingRow + i, startingCol + j);
				}
			}
			return data;
		}
		
	}
