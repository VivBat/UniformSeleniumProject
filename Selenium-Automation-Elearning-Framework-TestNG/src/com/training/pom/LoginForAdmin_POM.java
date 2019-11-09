package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//POM class to login for admin
public class LoginForAdmin_POM {
	
	//variables
	private WebDriver driver; 
	
	//constructor
	public LoginForAdmin_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locator for username field
	@FindBy(id="input-username")
	private WebElement userName;
	
	//locator for password field
	@FindBy(id="input-password")
	private WebElement password; 
	
	//locator for login button
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement loginBtn; 
	
	//method to enter username
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	//method to enter password
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	//method to click on login button
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	//method to assert page title once the user logs in
	public void assertPageTitle() {
		assertEquals(driver.getTitle(), "Dashboard");
	}
	
	//method to print page title
	public void printPageTitle() {
		System.out.println("Page title asserted:  "+ driver.getTitle());
	}
	
	//method to do all the steps in one go. enter username, password, click login in button
	public void loginForAdminMethod(String uname, String pass) {
		this.userName.clear();
		this.userName.sendKeys(uname);
		this.password.clear(); 
		this.password.sendKeys(pass); 
		this.loginBtn.click(); 
		
	}
	
	//method to assert error msg if the user enters wrong credentials
	public void assertForWrongCreds() {
		assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText().substring(0, 38), "No match for Username and/or Password.");
		System.out.println("Asserted that error has been displayed on scrren: " +driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText().substring(0, 38));
	}
	
}


