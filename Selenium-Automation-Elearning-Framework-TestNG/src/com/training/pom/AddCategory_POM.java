package com.training.pom;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


//POM class to add a category. Used by Admin//
public class AddCategory_POM {
	
	
	//declaring the variables
	private GoingThroughAllCategories_POM goThruTable;	
	private WebDriver driver; 
	private String catAdded;
	
	//declaring the constructor
	public AddCategory_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating tag button to hover over under which categories button lies
	@FindBy(xpath="//i[@class='fa fa-tags fa-fw']")
	private WebElement tagButton;
	
	//locating categories button
	@FindBy(xpath="//li[@id='catalog']/ul[1]/li[1]/a[1]")
	private WebElement categoriesBtn; 
	
	//locating add new button
	@FindBy(xpath="//a[@data-original-title='Add New']")
	private WebElement addNewBtn;
	
	//locating category name field to send keys to
	@FindBy(name="category_description[1][name]")
	private WebElement catName;
	
	//locating meta tag title field
	@FindBy(name="category_description[1][meta_title]")
	private WebElement metaTag;
	
	//locating the save button
	@FindBy(xpath="//button[@data-original-title='Save']")
	private WebElement saveBtn;
		
	//locating success msg that appears after adding a category to assert
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement successMsg;	
	
	//locating pagination to find total number of pages
	@FindBy(xpath="//ul[@class='pagination']/li")
	private List<WebElement> paginationCount;
	
	//locator for category names in the tables on all the pages
	@FindBy(xpath="//tbody/tr/td[2]")
	private List<WebElement> catNameOnPages;
	
	//method to go into categories
	public void goIntoCategories() {
		Actions act = new Actions(driver);
		//hovering over the tag button
		act.moveToElement(this.tagButton).build().perform();
		//clicking on categories button
		this.categoriesBtn.click();
	}
	
	//method to add a new category
	public void addNewCategory(String name, String metaTag) {
		
		//clicking on add button
		this.addNewBtn.click();
		
		//name of category passed assigned to catAdded variable
		this.catAdded = name;
		
		//entering the name of category in the category name field
		this.catName.sendKeys(name);
		
		//entering meta tag title for the category
		this.metaTag.sendKeys(metaTag);
		
		//clicking on save button
		this.saveBtn.click();		
	}
	
	//method to assert that success msg has been displayed on screen
	public void assertMsgCheck() {
		assertEquals(successMsg.getText().substring(0,38), "Success: You have modified categories!");
		System.out.println("Asserted that the msg has been displayed:  "+ successMsg.getText().substring(0,38));
	}
	
	//method to call GoingThroughAllCategories_POM to go through every category in the table to
	//verify if that category has been added to the table
	public void call_GoingThroughAllCategories_POM() {
		
	   //creating an object of GoingThroughAllCategories_POM
	   goThruTable = new GoingThroughAllCategories_POM(driver);
	   
	   //calling method from GoingThroughAllCategories_POM to set the value of variables which will be searched for in the table
	   goThruTable.setDesiredCategory(catAdded);
	   
	   //calling method which checks whether the category (catAdded) is present in the table
	   goThruTable.catAddedToTableCheck();
	   
	   //calling method which prints the count of total no of categories present in the table
	   goThruTable.printTotalCountOfCats();
	   
	   //calling method which prints the count of our category(the one that we have added)
	   goThruTable.printCountOfDesiredCat();
	
	}
		
	//method to do all of the above in one go//
	public void addCategoryMethod(String cname, String mtag) {
		goIntoCategories(); 
		addNewCategory(cname, mtag);
		assertMsgCheck();
		call_GoingThroughAllCategories_POM();
		
	}
	
}
	





