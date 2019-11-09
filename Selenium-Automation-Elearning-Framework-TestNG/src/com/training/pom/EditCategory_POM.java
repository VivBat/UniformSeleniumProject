package com.training.pom;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//POM class to edit a category. Used by Admin//
public class EditCategory_POM {
	
	//variables
	private WebDriver driver; 
	
	//constructor
	public EditCategory_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating edit button
	@FindBy(xpath="//tbody/tr[1]/td[4]/a[1]")
	private WebElement editButton;
	
	//locating meta tag title field
	@FindBy(name="category_description[1][meta_title]")
	private WebElement metaTagField; 
	
	//locating the save button
	@FindBy(xpath="//button[@data-original-title='Save']")
	private WebElement saveBtn;
		
	//locating success msg that appears after editing to assert
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement successMsg;	
	

	//method to add a new category
	public void editMetaTag(String newTag) {
		
		//clicking on edit button
		this.editButton.click();
		
		//sending keys to meta tag field (the field that we want to edit)
		this.metaTagField.sendKeys(newTag);
		
		//clikcing on save button after editing the field
		this.saveBtn.click();		
		
	}
	
	//method to assert that success msg has been displayed on screen
	public void assertMsgCheck() {
		assertEquals(successMsg.getText().substring(0,38), "Success: You have modified categories!");
		System.out.println("Asserted that the msg has been displayed:  "+ successMsg.getText().substring(0,38));
	}
	
	
	//method to do all of the above in one go
	public void editMetaTagMethod(String ntag) {
		editMetaTag(ntag);
		assertMsgCheck();
		
	}
	
}
	






