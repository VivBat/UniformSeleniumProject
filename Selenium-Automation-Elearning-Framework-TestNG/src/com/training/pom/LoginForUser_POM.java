package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//POM class to login to non admin user
public class LoginForUser_POM {
	
	//variables
	private WebDriver driver; 
	
	//constructor
	public LoginForUser_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locator for the doll icon which reveals the option for login
	@FindBy(css="i.fa-user")
	private WebElement dollIcon;
	
	//locator for login option button
	@FindBy(xpath="//a[contains(text(), 'Login')]")
	private WebElement loginOption; 
	
	//locator for username field
	@FindBy(id="input-email")
	private WebElement userName; 
	
	//locator for password field
	@FindBy(id="input-password")
	private WebElement password;
	
	//locator for login button
	@FindBy(xpath="//input[@value='Login']")
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
	
	//method to click login button
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	//method to click on doll icon which contains the login button
	public void clickButton1() {
		this.dollIcon.click();
	}
	
	//method to click on login option button
	public void clickButton2() {
		this.loginOption.click();
	}
	
	//method to do all of the above in one go
	public void loginUser(String uname, String pass) {
		this.dollIcon.click();
		this.loginOption.click();
		this.userName.sendKeys(uname);;
		this.password.sendKeys(pass);;
		this.loginBtn.click();
	}
}

