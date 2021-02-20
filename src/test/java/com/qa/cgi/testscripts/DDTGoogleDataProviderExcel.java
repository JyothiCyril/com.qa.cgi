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
import com.qa.cgi.utilities.ExcelUtilites;

public class DDTGoogleDataProviderExcel {
	
	
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
	
	@Test(dataProvider = "testData")// test method is receiving the data from SearchData method
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
		
		GoogleOR.setinputSearchTextBoxSubmit();
		
		// getTitle and chack if it is same as search term
		
		
		Assert.assertTrue(Driver.getTitle().contains(SearchTerm));
		
	}
	
	@DataProvider(name="testData") // method provides data to another method (@Test) 
	public Object[][] SearchData(){
		
		Object data[][] = testData("D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\src\\com\\qa\\Testdata\\InputData.xlsx" , "Sheet1");
		return data;
	}
	
	
	
	public static Object[][] testData(String FilePath, String SheetName){
		
		ExcelUtilites EUtil = new ExcelUtilites(FilePath, SheetName);
		int rowCount = EUtil.getRowCount();
		int colCount = EUtil.getColCount();
		
		Object data[][] = new Object[rowCount-1][colCount];
		
		for(int i=1; i<rowCount ; i++) {
			for(int j=0 ; j<colCount ; j++) {
				String cellData = EUtil.getCellData(i, j);
				
				data[i-1][j] = cellData;
			}
		}
		
		
		return data;
		
	}
	
	

}
