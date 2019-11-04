package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginForUser_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginForUser {

	private WebDriver driver;
	private String baseUrl;
	private LoginForUser_POM UFM_006_loginPOM;
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
		UFM_006_loginPOM = new LoginForUser_POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
//		implicitWait = properties.getProperty("implicitWait");
//		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
//		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.quit();
	}
	@Test
	public void validLoginTest() {
		UFM_006_loginPOM.clickButton1();
		UFM_006_loginPOM.clickButton2();
		UFM_006_loginPOM.sendUserName("batsy@cave.com");
		UFM_006_loginPOM.sendPassword("ghi@123");
		UFM_006_loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot();
	}
}
