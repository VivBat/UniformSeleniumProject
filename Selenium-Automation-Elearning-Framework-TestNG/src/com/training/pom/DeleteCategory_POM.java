package com.training.pom;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//POM class to delete categories. Used by Admin//
public class DeleteCategory_POM {
	
	//variables
	private WebDriver driver; 
    private GoingThroughAllCategories_POM goThruTable;
	
    //constructor
	public DeleteCategory_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	//locating pagination to find total number of pages
	@FindBy(xpath="//ul[@class='pagination']/li")
	private List<WebElement> paginationCount;
	
	//locator for category names in the tables on all the pages
	@FindBy(xpath="//tbody/tr/td[2]")
	private List<WebElement> catNameOnPages;
	
	//locator for message that appears when categories are deleted
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement successMsg;	
	
	
	//method that calls mehtod from GoingThroughAllCategories_POM which looks for the categories passed to it
	//in the table. If it finds those categories in the table the tick boxes against those are ticked and then delete button is clicked
	public void deleteListedItems(List<String> lst ) {
		
		//creating an object of GoingThroughAllCategories_POM
		goThruTable = new GoingThroughAllCategories_POM(driver);
		//calling tickListedItemAndDelete from GoingThroughAllCategories_POM
		goThruTable.tickListedItemAndDelete(lst);
		
	}
	
	//method to assert that success msg has been displayed on screen
		public void assertMsgCheck() {
			assertEquals(successMsg.getText().substring(0,38), "Success: You have modified categories!");
			System.out.println("Asserted that the msg has been displayed:  "+ successMsg.getText().substring(0,38));
		}
		
	
	
  
}
	





