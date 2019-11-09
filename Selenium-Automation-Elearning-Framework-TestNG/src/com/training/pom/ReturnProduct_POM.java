package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//POM class to return an ordered product
public class ReturnProduct_POM {
	
	//variables
	private WebDriver driver; 
	private String assertMsg;
	
	//constructor
	public ReturnProduct_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating the my account btn
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement myAcctBtn;
	
	//locating order history button
	@FindBy(linkText="Order History")
	private WebElement orderHistoryBtn; 
	
	//locating view product details from the table button
	@FindBy(xpath="//tbody/tr[1]/td[7]/a")
	private WebElement viewDetailsBtn;
	
	//locating return button
	@FindBy(css="i.fa-reply")
	private WebElement returnBtn;
	
	//locating reason radio button
	@FindBy(xpath="//input[@name='return_reason_id' and @value='1']")
	private WebElement reasonBtn;
	
	//locating agree checkbox
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreeChkbox;
		
	//locating submit button
	@FindBy(xpath="//input[@value='Submit']")
	private WebElement submitBtn;	
	
	
	//method to return a product
	public void returnProductMethod() {
		
		//clicking on my account button to go to my account details
		this.myAcctBtn.click();
		
		//viewing order history
		this.orderHistoryBtn.click();
		
		//viewing order details
		this.viewDetailsBtn.click();
		
		//clicking on return button
		this.returnBtn.click();
		
		//clicking on reason for return button
		this.reasonBtn.click();
		
		//clicking on agree terms checkbox
		this.agreeChkbox.click();
		
		//submitting
		this.submitBtn.click();
		
		//asserting page title to check that user is taken to product returns page 
		assertEquals(driver.getTitle(), "Product Returns");
	    System.out.println("page title asserted after returning the product: "+ "Product Returns");

	
	}
	
	
}
	





