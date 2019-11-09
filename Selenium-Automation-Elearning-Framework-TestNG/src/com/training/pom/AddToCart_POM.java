package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//POM class to add an item to cart//
public class AddToCart_POM {
	
	//variables
	private WebDriver driver; 
	private String assertMsg;
	
	//constructor
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
	
	
	//method to choose an item from the catalogue
	public void chooseItem() {
		
		//clicking on premium school uniforms card
		this.preSchUniCard.click();
		
		//clicking on an item to add it to cart
		this.itemChosen.click();
	}
	
	//method to select a size of the item
	public void selectSize() {
		new Select(sizeSelected).selectByIndex(3);
	}
	
	//method to click on add to cart button
	public void addToCartBtn() {
		this.addBtn.click(); 
	}
	
	//method to assert the success msg that is displayed once an item is added to cart
	public void assertSuccessMsg() {
		assertMsg = successText.getText().substring(0, 70);
		assertEquals(assertMsg, "Success: You have added REGULAR T-SHIRTS (Rust) to your shopping cart!"); 
	}
	
	//method to print the success msg
	public void printSuccessMsg() {
		 System.out.println("Asserted that msg: "+ assertMsg +"  :displayed on screen"); 
	}
	
	//method to do all of the above in one go
	public void addedToCart() {
		this.preSchUniCard.click();
		this.itemChosen.click();
		new Select(sizeSelected).selectByIndex(3);
		this.addBtn.click();
		assertMsg = successText.getText().substring(0, 70);
		assertEquals(assertMsg, "Success: You have added REGULAR T-SHIRTS (Rust) to your shopping cart!"); 
	}
	
}
	



