package com.training.pom;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//POM class that goes through all the categories that are there in the table//
public class GoingThroughAllCategories_POM {
	
	//variables
	private WebDriver driver; 
    private int TotalCountOfCategories=0;
    private int CountOfCatBeingLookedFor=0;
    private String desiredCategory;
	private List<WebElement> list;
	
	//constructor
	public GoingThroughAllCategories_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	//locating pagination to find total number of pages
	@FindBy(xpath="//ul[@class='pagination']/li")
	private List<WebElement> paginationCount;
	
	//locator for category names in the tables on all the pages
	@FindBy(xpath="//tbody/tr/td[2]")
	private List<WebElement> catNameOnPages;
	
	//locate delete button
	@FindBy(xpath="//button[@data-original-title='Delete']")
	private WebElement deleteBtn;
		
	//method that is used to set the category that we want to look for in the table
	public void setDesiredCategory(String cat) {
		this.desiredCategory=cat;		
	}
	
	//method to check that the added category is now visible in the table
	public void catAddedToTableCheck() {
		
		//total number of pages in the table
		int paginationSize = paginationCount.size();
		
		//going through all the pages looking for the added category##################################################
		for(int i=0; i<paginationSize-3; i++) {
			
			//count of total categories on single page
			TotalCountOfCategories+=catNameOnPages.size();
			
			//loop for going through all the categories on a single page
			for(WebElement j:catNameOnPages) {				
               //to see if the text of category matches the category we are looking for
   	    	   if(j.getText().contentEquals(desiredCategory)) {
   	    		   //if the text math matches the category we are looking for, increase the count of CountOfCatBeingLookedFor by 1
   	    		   CountOfCatBeingLookedFor++;
   	    		   //to assert that the category in table matches the category we are looking for
   	    		   assertEquals(j.getText(), desiredCategory); 
                   System.out.println("Asserted that  "+j.getText()+ " found in the table");
   	    	   }
   	        }
			
			//then click on next button to go to the next page of the table
   		    driver.findElement(By.linkText(">")).click();
		}
		
		    //the above loop to go through all pages doesnt include the last page
		    //so this loop is to collect categories of the last page************************************
		    
		    //get the count of categories from last page
		    TotalCountOfCategories+=catNameOnPages.size();
		    
		    for(WebElement j:catNameOnPages) {	    	
	    	     if(j.getText().contentEquals(desiredCategory)) {
	    	    	 CountOfCatBeingLookedFor++;
	    		      assertEquals(j.getText(), desiredCategory); 
                      System.out.println("Asserted that  "+j.getText()+ " found in the table");
	    	     }
	        }
	        //******************************************************************************************
		//#############################################################################################################
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//METHOD TO TICK THE ITEM THAT WE WANT AND THEN DELETE THOSE/////////////
	//list of items that we want to delete is passed to this method
	public void tickListedItemAndDelete(List<String> listOfItemToBeTicked) {
		
		//to count the number of pages in the table
        int paginationSize = paginationCount.size();
        
      
		//going through all the pages looking for the added category##################################################
		for(int i=0; i<paginationSize-3; i++) {
			
			//to count the number of categories on a page
			TotalCountOfCategories+=catNameOnPages.size();
			
			//to go through all the categories on a page
			for(WebElement j:catNameOnPages) {				
                //check if the text of category in the table matches any item in the list of items that we want to delete
   	    	    if(listOfItemToBeTicked.contains(j.getText())) {   	    	    	  
   	    		       
   	    	    	   CountOfCatBeingLookedFor++;
   	    		       
   	    		       //a list that collects the locators to all the tickboxes that matches that cats that we want to delete
   	    		       list = driver.findElements(By.xpath("//td[contains(text(),'"+j.getText()+"')]//preceding-sibling::td"));	
   	    		              
   	    		              //loop to go through the above list and tick the boxes
   	    		              for(WebElement k:list) {
   	    			              k.click();	    			
   	    		              }
	    		  	   
   	    		       //printing that the cateogry has been found been found in the table
   	    	           System.out.println(j.getText()+ " found in the table");
   	    	    	 
   	    	    }   	    	 
   	        }
			
			//calling the deleteCategory method which deletes the selected categories from the table
			deleteCategory();
			
			//updating the count of pages after the categories are deleted
			paginationSize = paginationCount.size();
		    
			//clicking on next to go to the next page
   		    driver.findElement(By.linkText(">")).click();
		}
		   
		//the above loop to go through all pages doesnt include the last page
	    //so this loop is to collect categories of the last page************************************
	    
		 TotalCountOfCategories+=catNameOnPages.size();    
		 for(WebElement j:catNameOnPages) {	    	
	    	     if(listOfItemToBeTicked.contains(j.getText())) {
	    	    	 CountOfCatBeingLookedFor++;
	    	    	 list = driver.findElements(By.xpath("//td[contains(text(),'"+j.getText()+"')]//preceding-sibling::td"));
	    	    	 for(WebElement k:list) {
  			              k.click();	    			
  		              }
                     System.out.println(j.getText()+ " found in the table");
	    	     }
	        }
	        //******************************************************************************************
		//#############################################################################################################
		 deleteCategory(); 
		
	}
	
	//method to delete the selected categories
	public void deleteCategory() {
		
		//clicking on delete button
		this.deleteBtn.click();
		
		//accepting the alert to confirm the deletion
		driver.switchTo().alert().accept();
	}
	
	//method to print the total number of categories in the table
    public void printTotalCountOfCats() {
	     System.out.println(TotalCountOfCategories);
    }
   
    //method to print the total number of categories in the table that we are looking for
    public void printCountOfDesiredCat() {
	     System.out.println(CountOfCatBeingLookedFor);
    }
	
}
	





