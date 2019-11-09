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
import com.training.pom.AddToCart_POM;
import com.training.pom.LoginForUser_POM;
import com.training.pom.RemoveFromCart_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Test to check that an added item can be removed from the cart
public class UFM_009_RemoveFromCart {

	//variables
	private WebDriver driver;
	private String baseUrl;
	private AddToCart_POM AUT_addToCart;
	private RemoveFromCart_POM AUT_removeFromCart;
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
		AUT_removeFromCart = new RemoveFromCart_POM(driver);
		baseUrl = properties.getProperty("baseURL");
		new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test
	public void removeFromCart() {
		
		//calling method from AddToCart_POM to add an item to cart
		AUT_addToCart.addedToCart();
		
		
		//calling method from RemoveFromCart_POM to remove the added item
		AUT_removeFromCart.cartButton();
		AUT_removeFromCart.viewCart();
		AUT_removeFromCart.removeButton();
		AUT_removeFromCart.assertSuccessMsg();
		AUT_removeFromCart.printSuccessMsg();
		
	}
}


