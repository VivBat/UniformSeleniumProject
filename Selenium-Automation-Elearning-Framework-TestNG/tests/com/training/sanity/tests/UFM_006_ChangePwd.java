package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ChangePwd_POM;
import com.training.pom.LoginForUser_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Test to change the password for a user
public class UFM_006_ChangePwd {

	//variables
	private WebDriver driver;
	private String baseUrl;	
	private LoginForUser_POM loginToApp;
	private ChangePwd_POM UFM_006_changePwdPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		UFM_006_changePwdPOM = new ChangePwd_POM(driver); 
		loginToApp = new LoginForUser_POM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	
	
	public void changePassword() {
		
	   //logging to the app first using LoginForUser_POM
	   loginToApp.loginUser("batsy@cave.com", "ghi@123");

	   //calling methods from ChangePwd_POM to change the password
	   UFM_006_changePwdPOM.findOptionToChange();
	   UFM_006_changePwdPOM.newPassword("jkl@123");
	   UFM_006_changePwdPOM.confirmPassword("jkl@123");
	   UFM_006_changePwdPOM.clickContinueBtn();
	   UFM_006_changePwdPOM.assertTheChange();
	   UFM_006_changePwdPOM.printNewPassword();
	}
}
