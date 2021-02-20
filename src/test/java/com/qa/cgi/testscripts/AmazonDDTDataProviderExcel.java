package com.qa.cgi.testscripts;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.cgi.pages.AmazonPages;
import com.qa.cgi.utilities.ExcelUtilites;





public class AmazonDDTDataProviderExcel {
		
		
	WebDriver Driver;
	AmazonPages AmazonOR;
		
		
		
		@Test(dataProvider = "testData")
		public void SearchItem(String Category, String ItemName) throws IOException {
							
				System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\Drivers\\chromedriver.exe");
				Driver = new ChromeDriver();
				AmazonOR = new AmazonPages(Driver);
				
				Driver.get("https://www.amazon.in/");
								
				AmazonOR.SetCategoryList(Category); // category
				
				AmazonOR.setSearchInput(ItemName); // search item
				
				AmazonOR.setMagnifierBtn();		
				
				List<WebElement> getAllItemName = AmazonOR.GetAllItemName();
				
				for(WebElement item : getAllItemName) {
					
					System.out.println(item.getText());
					
				}
				
				Driver.close();
			}
			
			
		@DataProvider(name="testData") // method provides data to another method (@Test) 
		public Object[][] SearchData(){
			
			Object data[][] = testData("D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\src\\com\\qa\\Testdata\\InputData.xlsx" , "Sheet2");
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
	
	
	
