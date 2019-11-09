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
import com.training.pom.AddCategory_POM;
import com.training.pom.EditCategory_POM;
import com.training.pom.LoginForAdmin_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//test to edit category//
public class UNF_039_EditCategory {

	//variables
	private WebDriver driver;
	private String adminURL;
	private LoginForAdmin_POM AUT_loginAdmin;
	private AddCategory_POM AUT_addCat;
	private EditCategory_POM AUT_editMeta;
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
		AUT_addCat = new AddCategory_POM(driver);
		AUT_editMeta = new EditCategory_POM(driver);
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
	public void EditCategory() {
		//login with userid and password passed as arguments
		AUT_loginAdmin.loginForAdminMethod("admin", "admin@123");
		
		//go into categories
		AUT_addCat.goIntoCategories();
		
		//editing meta tag
		AUT_editMeta.editMetaTagMethod("new tag");
		
	}
}




