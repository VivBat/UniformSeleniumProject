package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginForAdmin_POM;
import com.training.pom.LoginForUser_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Test to check that the admin cannot login with wrong credentials//
public class UNF_037_InvalidLogin {

	//variables
	private WebDriver driver;
	private String adminURL;
	private LoginForAdmin_POM AUT_loginAdmin;  
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
	@Test
	//login with invalid credentials//
	public void validLoginTest() {
		
		//calling methods from LoginForAdmin_POM//
		
		//passing invalid credentials
		AUT_loginAdmin.loginForAdminMethod("ADBIN", "ajscdaca");
		
		//method to assert whether error msg displayed on screen
		AUT_loginAdmin.assertForWrongCreds();
		
	}
}


