package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


//POM class to add products. Used by Admin//
public class AddProducts_POM {
	
	
	private WebDriver driver; 

	//declaring the constructor
	public AddProducts_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//locating tag button to hover over under which products button lies
	@FindBy(xpath="//i[@class='fa fa-tags fa-fw']")
	private WebElement tagButton;
	
	//locating products button
	@FindBy(xpath="//li[@id='catalog']/ul[1]/li[2]/a[1]")
	private WebElement productsBtn; 
	
	//locating add new button
	@FindBy(xpath="//a[@data-original-title='Add New']")
	private WebElement addNewBtn;
	
	//locating product name field to send keys to
	@FindBy(name="product_description[1][name]")
	private WebElement productName;
	
	//locating meta tag title field
	@FindBy(name="product_description[1][meta_title]")
	private WebElement metaTag;
	
	//locator to go to data tab
	@FindBy(xpath="//a[contains(text(),'Data')]")
	private WebElement dataTabBtn;
	
	//locating the model field
    @FindBy(id="input-model")
	private WebElement modelField;
	
    //locating the price field
    @FindBy(id="input-price")
	private WebElement priceField;
    
    //locator to go to links tab
  	@FindBy(xpath="//a[contains(text(),'Links')]")
  	private WebElement linksTabBtn;
    
    //locating the categories field
    @FindBy(id="input-category")
	private WebElement categoriesField;
  	
	//locating the save button
	@FindBy(xpath="//button[@data-original-title='Save']")
	private WebElement saveBtn;
		
	//locating success msg that appears after adding a product to assert
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement successMsg;	
	
	//locating error msg that appears after trying to add a product unsuccessfuly to assert
	@FindBy(xpath="//div[@class='alert alert-danger']")
	private WebElement errorMsg;	
	
	//method to go into products
	public void goIntoProducts() {
		Actions act = new Actions(driver);
		//hovering over the tag button
		act.moveToElement(this.tagButton).build().perform();
		//clicking on products button
		this.productsBtn.click();
	}
	
	//method to add a new product
	
	public void addNewProduct(String name, String metaTag, String model, double price, String category) {
		
		//clicking on add button
		this.addNewBtn.click();
		
		//entering the name of product in the category name field
		this.productName.sendKeys(name);
		
		//entering meta tag title for the product
		this.metaTag.sendKeys(metaTag);
		
		//going to model tab
		this.dataTabBtn.click();
		
		//entering model no
		this.modelField.sendKeys(model);
		
		//entering price
		this.priceField.sendKeys(String.valueOf(price));
		
		//going to links tab
		this.linksTabBtn.click();
		
		//entering categories
		this.categoriesField.sendKeys(category);
		
		//clicking on save button
		this.saveBtn.click();		
	}
	
	//method to assert that success msg has been displayed on screen
	public void assertMsgCheck() {
		assertEquals(successMsg.getText().substring(0,36), "Success: You have modified products!");
		System.out.println("Asserted that the msg has been displayed:  "+ successMsg.getText().substring(0,36));
	}
	
	//method to assert that ERROR msg has been displayed on screen
		public void assertErrorMsgCheck() {
			assertEquals(errorMsg.getText().substring(0,52), "Warning: Please check the form carefully for errors!");
			System.out.println("Asserted that the msg has been displayed:  "+ errorMsg.getText().substring(0,52));
		}
		
			
	
}
	





