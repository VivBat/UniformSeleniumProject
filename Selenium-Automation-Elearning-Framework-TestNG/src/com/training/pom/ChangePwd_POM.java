package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePwd_POM {
	private WebDriver driver; 
	private String newPWD;
	
	public ChangePwd_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating change your password option
	@FindBy(linkText="Change your password")
	private WebElement changeOption;
	
	//giving in the new password
	@FindBy(id="input-password")
	private WebElement newPassword; 
	
	//confirming the new password
	@FindBy(id="input-confirm")
	private WebElement confirmPassword; 
	
	//clicking on continue to change the password
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	
	public void findOptionToChange() {
		this.changeOption.click();
	}
	
	public void newPassword(String newPass) {
		this.newPassword.clear();
		this.newPassword.sendKeys(newPass);
	}
	
	public void confirmPassword(String newPass) {
		this.confirmPassword.clear(); 
		this.confirmPassword.sendKeys(newPass); 
		newPWD=newPass;
	}
	
	public void clickContinueBtn() {
		this.continueButton.click(); 
	}
	
	//printing the new password on console
	public void printNewPassword() {
		System.out.println(newPWD);
	}
	
	//locating the text confirming the password change
		@FindBy(css="div.alert-success")
		private WebElement assertText;
		
	
	//asserting that the text confirming password change has been displayed on screen
	public void assertTheChange() {
		 assertEquals(assertText.getText(), "Success: Your password has been successfully updated.");
	    	
	}
	
}

