package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.UNF_069_Dataproviders;
import com.training.generics.ScreenShot;

import com.training.pom.AddProducts_POM;
import com.training.pom.LoginForAdmin_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Test to add multiple products from excel file. Used by admin//
public class UNF_069 {

	//variables
	private WebDriver driver;
	private String adminURL;
	private LoginForAdmin_POM AUT_loginAdmin;
	private AddProducts_POM AUT_addNewProduct;
	private static Properties properties;

	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		AUT_loginAdmin = new LoginForAdmin_POM(driver); 
		AUT_addNewProduct = new AddProducts_POM(driver);
		adminURL = properties.getProperty("adminURL");
		new ScreenShot(driver); 
		// open the browser 
		driver.get(adminURL);

	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	
	
	
	@Test(dataProvider="UNFTD_008", dataProviderClass = UNF_069_Dataproviders.class)
	//dataProviderClass is under com.training.dataproviders
	public void addMultipleProducts(String name, String metaTag, String model, double price, String category) {
		
		//logging in using LoginForAdmin_POM
		AUT_loginAdmin.loginForAdminMethod("admin", "admin@123");
		
		//going to products option using method from AddProducts_POM
		AUT_addNewProduct.goIntoProducts();
		
		//passing arguments(which data provider gets from excel) to method from AddProducts_POM
		AUT_addNewProduct.addNewProduct(name, metaTag, model, price, category);
		
		//aserting the msg that is displayed when a product is added successfully
		AUT_addNewProduct.assertErrorMsgCheck();
		
}
}





