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

public class UFM_009_RemoveFromCart {

	private WebDriver driver;
	private String baseUrl;
	private AddToCart_POM addToCart;
	private RemoveFromCart_POM removeFromCart;
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
		addToCart = new AddToCart_POM(driver);
		removeFromCart = new RemoveFromCart_POM(driver);
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
	public void removeFromCart() {
		
		//first add an item to the cart using addToCart POM
		addToCart.chooseItem();
		addToCart.selectSize();
		addToCart.addToCartBtn();
		addToCart.assertSuccessMsg();
		addToCart.printSuccessMsg();
		
		//remove the added item
		removeFromCart.cartButton();
		removeFromCart.viewCart();
		removeFromCart.removeButton();
		removeFromCart.assertSuccessMsg();
		removeFromCart.printSuccessMsg();
		
	}
}


