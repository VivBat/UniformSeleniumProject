package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddToCart_POM {
	private WebDriver driver; 
	private String assertMsg;
	
	public AddToCart_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating premium school uniforms card on home page
	@FindBy(css="img[alt='banner1']")
	private WebElement preSchUniCard;
	
	//locating a product
	@FindBy(xpath="//div[@id='featured-grid']//child::img[@alt='REGULAR T-SHIRTS (Rust)']")
	private WebElement itemChosen; 
	
	//locating size selection element
	@FindBy(id="input-option376")
	private WebElement sizeSelected;
	
	//locating add to cart button
	@FindBy(id="button-cart")
	private WebElement addBtn;
	
	//locating text to assert that item has been added
	@FindBy(css="div.alert-success")
	private WebElement successText;
	
	public void chooseItem() {
		this.preSchUniCard.click();
		this.itemChosen.click();
	}
	
	public void selectSize() {
		new Select(sizeSelected).selectByValue("964");
	}
	
	public void addToCartBtn() {
		this.addBtn.click(); 
	}
	
	public void assertSuccessMsg() {
		assertMsg = successText.getText().substring(0, 70);
		assertEquals(assertMsg, "Success: You have added REGULAR T-SHIRTS (Rust) to your shopping cart!"); 
	}
	
	public void printSuccessMsg() {
		 System.out.println("Asserted that msg: "+ assertMsg +"  :displayed on screen"); 
	}
	

}

