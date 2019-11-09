package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//POM class to test error scenario when the password entered in new password and confirm password fields do not match
public class UFM_007_Error_POM {
	
	//variables
	private WebDriver driver; 
	private String newPWD;
	private String errorMsg;
	
	//constructor
	public UFM_007_Error_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating the error message
	@FindBy(css="div.text-danger")
	private WebElement errorMessage;
	
	
	//method to assert that erro msg is displayed when the password in confirm field doesnt match
	public void assertErrorMessage() {
		assertEquals(errorMessage.getText(), "Password confirmation does not match password!");
		errorMsg=errorMessage.getText();
	}
	
	//printing the error msg
	public void printErrorMessage() {
		System.out.println("Asserted that the text: "+ errorMsg +" :has been displayed to the user");
	}
	
	
	
}

