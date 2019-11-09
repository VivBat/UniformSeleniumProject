package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//POM class to checkout a product added to cart
public class ProductCheckout_POM {
	
	//variables
	private WebDriver driver; 
	
	//constructor
	public ProductCheckout_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating the checkout button
	@FindBy(linkText="Checkout")
	private WebElement checkouBtn;
	
	//locating address radio button
	@FindBy(xpath="//input[@name='payment_address' and @value='existing']")
	private WebElement addressRadio; 
	
	//locating address continue button
	@FindBy(id="button-payment-address")
	private WebElement addressContinue;
	
	//locating delivery details radio
	@FindBy(xpath="//input[@name='shipping_address' and @value='existing']")
	private WebElement delRadio;
	
	//locating delivery details continue button
	@FindBy(id="button-shipping-address")
	private WebElement delContinue;
	
	//locating delivery method continue button
	@FindBy(id="button-shipping-method")
	private WebElement delMethodContinue;
		
	//locating agree checkbox
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreeCheckbox;	
	
	//locating payment method continue button
	@FindBy(id="button-payment-method")
	private WebElement payMethodContinue;
	
	//locating confirm  button
	@FindBy(id="button-confirm")
	private WebElement confirmButton;
	
	//method to click on checkout button after a product has been added to the cart
	public void checkoutButtonClick() {
		this.checkouBtn.click();
	}
	
	//method to select added address and clicking on continur button
	public void addressContinue() {
		this.addressRadio.click();
		this.addressContinue.click();
	}
	
	//method to select delivery details and clikcing on continue
	public void deliveryDetailsContinue() {
		 JavascriptExecutor jse = ((JavascriptExecutor)driver);	
         jse.executeScript("arguments[0].click();",this.delRadio );
         jse.executeScript("arguments[0].click();",this.delContinue );
		 
	}
	
	//method to click on continue after delivery details
	public void deliveryMethodContinue() {
		this.delMethodContinue.click();
	}
	
	//method to click on agree terms tick box and clicking on continue
	public void paymentMethodContinue() {
		if(!this.agreeCheckbox.isSelected()) {
			JavascriptExecutor jse = ((JavascriptExecutor)driver);
            jse.executeScript("arguments[0].click();",this.agreeCheckbox);		 
	  }
		this.payMethodContinue.click();	  
	}
	
	//method to confirm the checkout
	public void confirmButton() {
		this.confirmButton.click();
	}
	
	//method to perform all steps in a single go
	public void checkOutMethod() {
		checkoutButtonClick();
		addressContinue();
		deliveryDetailsContinue();
		deliveryMethodContinue();
		paymentMethodContinue();
		confirmButton();
		
	   //assertion for the msg that appears confirming the checkout
	   assertEquals(driver.findElement(By.xpath("//*[@id='content']/p")).getAttribute("innerText"), "Your order has been successfully processed!");
	   System.out.println("Asserted that: "+ driver.findElement(By.xpath("//*[@id='content']/p")).getAttribute("innerText"));
		
	}
}
	




