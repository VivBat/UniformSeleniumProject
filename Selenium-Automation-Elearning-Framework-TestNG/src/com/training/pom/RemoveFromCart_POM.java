package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RemoveFromCart_POM {
	private WebDriver driver; 
	private String assertMsg;
	
	public RemoveFromCart_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating the cart button
	@FindBy(css="button.dropdown-toggle")
	private WebElement cartBtn;
	
	//locating the button to view the cart
	@FindBy(xpath="//a[@href='http://uniformm1.upskills.in/index.php?route=checkout/cart']")
	private WebElement viewCart; 
	
	//locating the remove button
	@FindBy(xpath="//button[@class='btn btn-danger']")
	private WebElement removeButton;
	
	//locating text to assert that the cart is empty
	@FindBy(xpath="//h1[contains(text(),'Shopping Cart')]//following::p[contains(text(),'Your shopping cart is empty')]")
	private WebElement successText;
	
	public void cartButton() {
		this.cartBtn.click();
	}
	
	public void viewCart() {
		this.viewCart.click();
	}
	
	public void removeButton() {
		this.removeButton.click(); 
	}
	
	public void assertSuccessMsg() {
		assertMsg = successText.getText();
		assertEquals(assertMsg, "Your shopping cart is empty!"); 
	}
	
	public void printSuccessMsg() {
		 System.out.println("Asserted that msg: "+ assertMsg +"  :displayed on screen"); 
	}
	

}


