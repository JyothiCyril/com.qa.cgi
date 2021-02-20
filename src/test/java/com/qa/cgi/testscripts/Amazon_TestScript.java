package com.qa.cgi.testscripts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.cgi.pages.AmazonPages;





public class Amazon_TestScript {

	
	/*
	 * 1. Launch the browser 
	 * 2. Run URL
	 * 
	 * 
	 * 3. Check if the page has 400 links
	 * 
	 * 4. Check what are the footer links
	 * 
	 * 5. Check what are the countries in the footer links
	 * 
	 * 6. Check if "Books" option available in the drop down list
	 * 
	 * 7. Select books as categories, Type da vinci code & click on magnigier button
	 * 
	 * 8. check if the step 7 impact the change of title same as item searched
	 * 
	 * 9. Get all the item name and print.
	 * 
	 * 
	 * 10. Browser to be closed
	 */
	
	
	
	WebDriver Driver;
	AmazonPages AmazonOR;
	SoftAssert SAssert;
	
	@Parameters({"Browser"})
	@BeforeTest // This method is executed once the test is initiated 
	// before all the methods the methods in the class
	public void setUp(String Browser) {
		// all instantiate of instance variable to be done
		
		if(Browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\Drivers\\chromedriver.exe");
			Driver = new ChromeDriver();
			
		}else if(Browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\Drivers\\geckodriver.exe");
			Driver = new FirefoxDriver();
			
		}else if(Browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\Drivers\\IEdriverServer.exe");
			Driver = new InternetExplorerDriver();
			
		}
		
			AmazonOR = new AmazonPages(Driver);
		SAssert = new SoftAssert();
	}
	
	
	@AfterTest // After all the methods in the class is executed..
	public void tearDown() {
		Driver.close();
	}
	
	@BeforeClass
	public void launchURL() {
		Driver.get("https://www.amazon.in/");
		
	}
	
	
	
	@Test(priority=1, groups= "Hyperlinks")
	public void checklinks() {
		
		int count = AmazonOR.getAllLinks().size();
		/*
		 * if(count==400) { System.out.println("Page has 400 hyperlinks"); // print only
		 * in console Reporter.log("Page has 400 hyperlinks"); // print only in HTML
		 * report Reporter.log("Page has 400 hyperlinks", true); // print in console &
		 * HTML report }else { System.out.println("Some of the links are missing"); }
		 */
		Assert.assertEquals(count, 400);
		
		Reporter.log("Page has 400 hyperlinks");
	
	}
	
	@Test(priority=2, groups= "Hyperlinks")
	
	public void FooterLinks() {
		
		List<WebElement> AllFooterLinks = AmazonOR.getAllFooterLinks();
		
		int size = AllFooterLinks.size();
		SAssert.assertEquals(size, 200);
		
		for(WebElement link: AllFooterLinks) {
			
			Reporter.log(link.getText());
		}
		SAssert.assertAll();
	}
	
	
	@Test(priority = 3,  groups= "Hyperlinks")
	public void CountriesList() {
		
		List<WebElement> allCountries = AmazonOR.getAllCountries();
		
		int size = allCountries.size();
		//Assert.assertEquals(size, 20);
		
		SAssert.assertEquals(size, 20);
		
		for(WebElement country: allCountries) {
			Reporter.log(country.getText());
			
		}
		
		SAssert.assertAll();
	}
	
	
	
}
