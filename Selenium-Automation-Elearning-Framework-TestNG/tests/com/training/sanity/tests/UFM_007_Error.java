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
import com.training.pom.LoginForUser_POM;
import com.training.pom.ChangePwd_POM;
import com.training.pom.UFM_007_Error_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UFM_007_Error {

	private WebDriver driver;
	private String baseUrl;
	private LoginForUser_POM loginPOM;
	private ChangePwd_POM changePWD;
	private UFM_007_Error_POM errorScenario;
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
		loginPOM = new LoginForUser_POM(driver); 
		changePWD = new ChangePwd_POM(driver);
		errorScenario = new UFM_007_Error_POM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void errorScenarioTest() {
		
		//loggin in using the login POM
		loginPOM.clickButton1();
		loginPOM.clickButton2();
		loginPOM.sendUserName("batsy@cave.com");
		loginPOM.sendPassword("def@123");
		loginPOM.clickLoginBtn(); 
//		UFM_006_loginPOM.captureScreenShot();
		
		//changing the password using change passwrod POM but with wrong password in confirm field
		changePWD.findOptionToChange();
		changePWD.newPassword("abcdef123");
		changePWD.confirmPassword("asdfegry1234");
		changePWD.clickContinueBtn();
		
		errorScenario.assertErrorMessage();
		errorScenario.printErrorMessage();
		
	}
}
