package com.qa.cgi.testscripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.cgi.pages.GooglePages;



public class DDTGoogleSearch {
	
	WebDriver Driver;
	GooglePages GoogleOR;
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\Drivers\\chromedriver.exe");
		Driver = new ChromeDriver();
		GoogleOR = new GooglePages(Driver);
		Driver.get("https://www.google.com/");
		Driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
	}

	
	@AfterTest
	public void tearDown() {
		Driver.close();
	}
	
	@Test(dataProvider="SearchData", priority=2)// test method is receiving the data from SearchData method
	public void GoogleSearch(String SearchTerm) throws InterruptedException {
		// type "Search item"
		GoogleOR.setinputSearchTextBox(SearchTerm);
		Thread.sleep(3000);
		
		//read all the items displayed in auto suggest
		List<WebElement> autoSuggest = GoogleOR.getAutoSuggest();
		for(WebElement item : autoSuggest) {
			Reporter.log(item.getText(),true);
		}
		
		// click on the button
		
		GoogleOR.setSearchButtonClick();
		
		// getTitle and chack if it is same as search term
		
		
		Assert.assertTrue(Driver.getTitle().contains(SearchTerm));
		
	}
	
	@DataProvider // method provides data to another method (@Test) 
	public Object[][] SearchData(){
		
		Object[][] data = new Object[3][1]; // 3 inputs one field to parameterize
		
		//Object[no. of inputs][no. of fields to be parameters]
		
		data[0][0] = "Automation Testing";
		data[1][0] = "Performance Testing";
		data[2][0] = "Software Testing";
		
		return data;
	}
	
	
	@Test(priority=2)
	public void checkTitle() {
		Assert.assertTrue(Driver.getTitle().contains("Google"));
		
	}
}
