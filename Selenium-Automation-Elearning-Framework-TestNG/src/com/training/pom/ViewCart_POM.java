package com.training.pom;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//POM class to view cart
public class ViewCart_POM {
	
	//variables
	private WebDriver driver; 
	private String assertMsg;
	
	//constructor
	public ViewCart_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating the cart menu button
	@FindBy(css="button.dropdown-toggle")
	private WebElement cartMenu;
	
	//locating the view cart button
	@FindBy(xpath="//ul[@class='dropdown-menu pull-right cart-menu']/li[2]/div/p/a[1]")
	private WebElement viewCart; 
	
	
	//clicking on cart menu button which reveals the option to view cart
	public void cartMenuButton() {
		 JavascriptExecutor jse = ((JavascriptExecutor)driver);				
         jse.executeScript("arguments[0].click();",this.cartMenu );
	}
	
	//clicking on view cart button
	public void viewCartButton() {
		 JavascriptExecutor jse = ((JavascriptExecutor)driver);				
         jse.executeScript("arguments[0].click();",this.viewCart );
	}
	
	//method to do all of the above in one go
	public void goToCart() {
		cartMenuButton() ;
		viewCartButton() ;
	}
}
	




