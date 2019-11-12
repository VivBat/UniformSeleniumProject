package com.training.utility;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//class that contains general excel related methods
public class ExcelUtils {
	
	static XSSFWorkbook workbook;
	static String projectPath;
    XSSFSheet sheet;
	static int rowCount;
	static int colCount;
	
	public ExcelUtils(String filePath, String sheetName) throws IOException {

	     	//to get the project location so that we dont have to hardcode filepath
		    projectPath = System.getProperty("user.dir");
		    
		    //workbook at location: projectPath + filePath
		    workbook = new XSSFWorkbook(projectPath + filePath);
			
		    //the sheet that contains our test data 
		    this.sheet = workbook.getSheet(sheetName);
		
	}
	
	//method that returns the total number of rows in the excel sheet
	public int getRowCount() {
		
		rowCount = sheet.getPhysicalNumberOfRows();
		return rowCount;
		
	}
	
	//method that returns the total number of columns in the sheet
    public int getColCount() {
		
		colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		return colCount;
		
	}
    
    //method that returns the String cell data of a cell
    public String getCellDataString(int rownum, int colnum) {
    	
    	String cellData = sheet.getRow(rownum).getCell(colnum).getStringCellValue();
    	return cellData;
   
    }
	
  //method that returns the numeric cell data of a cell
    public double getCellDataNumeric(int rownum, int colnum) {
    	
    	double cellData = sheet.getRow(rownum).getCell(colnum).getNumericCellValue();
    	return cellData;
   
    }

}
