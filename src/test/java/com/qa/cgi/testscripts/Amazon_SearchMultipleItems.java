package com.qa.cgi.testscripts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.cgi.pages.AmazonPages;





public class Amazon_SearchMultipleItems {
	
	WebDriver Driver;
	AmazonPages AmazonOR;
	//String Itemname;
	//String CategoryName;
	SoftAssert SAssert;
	
// WebDriver instance created --> Open the browser & Launch the URL
	// OR available, we need to initialize
	
	
	// Driver instance should be closed
	
	// categorize to check if there are any LoC of to be executed Once or many times --> Choose the appropriate test fixture
	// ( @BeforeTest, @BeforeClass, @BeforeMethod, @AfterTest, @AfterClass, @AfterMethod...
	
	// potential functionalities should be part of the @Test -- prioritized, grouped etc..
	
	
	/*
	 * 1. Total no. of links present on the page 
	 * 2. search for an item and get the
	 * details of the item 
	 * 3. get all the footer links and print the count and names
	 * 4. new user registration
	 * 5. Check the title of the amazon
	 *
	 */
	
	@BeforeTest
	public void setUp() {
		// Intantiate the WebDriver//	Instantiate the Pages OR
		
		System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\Drivers\\chromedriver.exe");
		Driver = new ChromeDriver();
		AmazonOR = new AmazonPages(Driver);
	//	Itemname = "Da Vinci Code";
	//	CategoryName = "Books";
		
		SAssert = new SoftAssert();
	}
	
	
	
	@AfterTest
	public void tearDown() {
		
		Driver.close();
		
	}
	
	
	@BeforeClass
	public void LaunchURL() {
		
		Driver.get("https://www.amazon.in/");
	}
	
	
	@AfterClass
	public void TestClosure() {
		
		System.out.println("Test completed ....");
	}
	
	
	@Test(priority=0, groups = "Validating Title")
	public void CheckTitle() {
		String Title = Driver.getTitle();
		
		//Assert.assertEquals(Title, "Welcome to Amazon");
		
		SAssert.assertEquals(Title, "Welcome to Amazon");
		
		/*
		 * if(Title.contains("amazon")) { System.out.println("CheckTitle is passed"); }
		 */
		Reporter.log(Title);
		
		SAssert.assertAll();
	}
	
	@Test(priority=2, groups = "Similar elements")
	public void getAllLinks() {
		
		
		List<WebElement> allLinks = AmazonOR.getAllLinks();
		
		System.out.println("Total no. of links are:" + allLinks.size());
		
		for(WebElement link : allLinks) {
			System.out.println(link.getAttribute("href"));
		}
		
		
	}
	
	
	@Test(priority=3, groups = "Similar elements")
	public void FooterLinks() {
		
		List<WebElement> allFooterLinks = AmazonOR.getAllFooterLinks();
		for(WebElement footer : allFooterLinks) {
			System.out.println(footer.getText());
		}
		
		
	}
	
	@Parameters({"Category","ItemName"})
	@Test(priority=4)
	public void SearchItem(String Category, String ItemName) {
		
		AmazonOR.SetCategoryList(Category);
		AmazonOR.setSearchInput(ItemName);
		AmazonOR.setMagnifierBtn();		
		
		
	}
	
	@Test(dependsOnMethods="SearchItem", priority=6)
	public void CheckItemsLoaded() {
		
		List<WebElement> getAllItemName = AmazonOR.GetAllItemName();
		
		for(WebElement item : getAllItemName) {
			
			System.out.println(item.getText());
			
		}
		
		
		
	}
	
	@Parameters({"ItemName"})
	@Test(priority=5, dependsOnMethods="SearchItem", groups = "Validating Title")
	public void CheckTitleLoaded(String ItemName) {
		String title = Driver.getTitle();
		/*
		 * if(title.contains(ItemName)) {
		 * System.out.println("Title is same as search term"); }
		 */
		Assert.assertTrue(title.contains(ItemName));
	}
	
	@Parameters({"Category"})
	@Test(priority=1)
	public void CheckFirstSelectedOptions(String Category) {
		// Books is selected by default
		
		if(AmazonOR.getFirstSelectedOption().contains(Category)) {
			System.out.println("Books is selected by default");
		}else {
			System.out.println("Books is not selected by default");
		}
		
		
	}
	
	
}
