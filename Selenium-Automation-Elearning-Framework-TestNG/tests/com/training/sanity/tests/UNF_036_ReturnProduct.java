package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddToCart_POM;
import com.training.pom.ChangePwd_POM;
import com.training.pom.LoginForUser_POM;
import com.training.pom.ProductCheckout_POM;
import com.training.pom.ReturnProduct_POM;
import com.training.pom.ViewCart_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//test to return an ordered product
public class UNF_036_ReturnProduct {

	//variables
	private WebDriver driver;
	private String baseUrl;	
	private LoginForUser_POM AUT_loginToApp;
	private AddToCart_POM AUT_addToCart;
	private ViewCart_POM AUT_viewCartGo;
	private ProductCheckout_POM AUT_checkoutItem;
	private ReturnProduct_POM AUT_returnItem;
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
		AUT_addToCart = new AddToCart_POM(driver);
		AUT_loginToApp = new LoginForUser_POM(driver);
		AUT_viewCartGo = new ViewCart_POM(driver);
		AUT_checkoutItem = new ProductCheckout_POM(driver);
		AUT_returnItem = new ReturnProduct_POM(driver);
		baseUrl = properties.getProperty("baseURL");
		new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void returnProduct() {
		//logging to the app first using Login_POM
	    AUT_loginToApp.loginUser("batsy@cave.com", "ghi@123");
	
	    //going to home page
	    driver.findElement(By.linkText("Uniform Store")).click();
	    
	    //adding to cart
	    AUT_addToCart.addedToCart();
	    
	    //viewing the cart
	    AUT_viewCartGo.goToCart();
	    
	    //checking out the product
	    AUT_checkoutItem.checkOutMethod();
	    
	    //return the product
	    AUT_returnItem.returnProductMethod();
	
    }
}
