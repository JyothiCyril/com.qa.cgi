package com.qa.cgi.testscripts;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.qa.cgi.pages.AmazonPages;

public class DDTusingCSV {
	
	//Provide CSV file path. It Is In D: Drive.
	 String CSV_PATH="D:\\SeleniumTraining\\com.qa.cgi\\src\\test\\java\\com\\qa\\cgi\\testdata\\Input.csv";
	 WebDriver driver;
	 AmazonPages AmazonOR;

	 
	 @BeforeTest
	 public void setup() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\Drivers\\chromedriver.exe");	 
	  driver = new ChromeDriver();
	  AmazonOR = new AmazonPages(driver);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.get("http://www.amazon.in");
	 }
	 
	 @Test
	 public void csvDataRead() throws IOException{
	  
	  CSVReader reader = new CSVReader(new FileReader(CSV_PATH));
	  String [] csvCell;
	  //while loop will be executed till the last line In CSV.
	  while ((csvCell = reader.readNext()) != null) {   
	   String Category = csvCell[0];
	   String ItemName = csvCell[1];
	   AmazonOR.SetCategoryList(Category);
	   AmazonOR.setSearchInput(ItemName);
	   AmazonOR.setMagnifierBtn();
	   System.out.println(driver.getTitle());
	   driver.quit();
	   
	  }  
	 }

}
