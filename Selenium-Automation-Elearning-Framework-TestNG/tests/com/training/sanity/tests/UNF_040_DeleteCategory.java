package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddCategory_POM;
import com.training.pom.DeleteCategory_POM;
import com.training.pom.EditCategory_POM;
import com.training.pom.LoginForAdmin_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Test to verify that multiple categories can be deleted at once by admin
public class UNF_040_DeleteCategory {

	//variables
	private WebDriver driver;
	private String adminURL;
	private LoginForAdmin_POM AUT_loginAdmin;
	private AddCategory_POM AUT_addCat;
	private DeleteCategory_POM AUT_deleteCats;
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
		AUT_deleteCats = new DeleteCategory_POM(driver);
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
    
	public void deleteCategory() {
		
		//login with userid and password passed as arguments
		AUT_loginAdmin.loginForAdminMethod("admin", "admin@123");
		
		//go into categories
		AUT_addCat.goIntoCategories();
		
		//delete the desired category
		AUT_deleteCats.deleteListedItems(Arrays.asList("Uniform", "Zoombox"));
		AUT_deleteCats.assertMsgCheck();
	}
}





