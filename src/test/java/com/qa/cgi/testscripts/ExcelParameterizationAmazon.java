package com.qa.cgi.testscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.cgi.pages.AmazonPages;





public class ExcelParameterizationAmazon {
	
	WebDriver Driver;
	AmazonPages AmazonOR;
	FileInputStream FileLoc;
	XSSFWorkbook WorkBook;
	XSSFSheet Sheet;
	
	
	
	@Test()
	public void SearchItem() throws IOException {
		
		FileLoc = new FileInputStream("D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\src\\com\\qa\\Testdata\\InputData.xlsx");
		WorkBook = new XSSFWorkbook(FileLoc);
		Sheet = WorkBook.getSheet("Sheet2");
		
		
		
		int lastRowNum = Sheet.getLastRowNum();
		
		int lastCellNum = Sheet.getRow(0).getLastCellNum();
		
		for(int i=1 ; i<=lastRowNum ; i++) {
			
			XSSFRow row = Sheet.getRow(i);
			
			System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\Drivers\\chromedriver.exe");
			Driver = new ChromeDriver();
			AmazonOR = new AmazonPages(Driver);
			
			Driver.get("https://www.amazon.in/");
			
			String Category = row.getCell(0).getStringCellValue();
			AmazonOR.SetCategoryList(Category); // category
			
			String ItemName = row.getCell(1).getStringCellValue();
			AmazonOR.setSearchInput(ItemName); // search item
			
			AmazonOR.setMagnifierBtn();		
			
			List<WebElement> getAllItemName = AmazonOR.GetAllItemName();
			
			for(WebElement item : getAllItemName) {
				
				System.out.println(item.getText());
				
			}
			
			Driver.close();
		}
		
		
			
	}
	
	
}
