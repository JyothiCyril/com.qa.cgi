package com.qa.allurereporting;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.cgi.pages.AmazonPages;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class SampleAllurerepotsscript {
	
	WebDriver Driver;
	AmazonPages AmazonOR;
	
	@BeforeClass
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		Driver = new ChromeDriver();
		AmazonOR = new AmazonPages(Driver);
		Driver.get("http://www.amazon.in");
		
	}
	
	@AfterClass
	public void tearDown() {
		Driver.quit();
	}
	
	@Test(priority=2, description = "Searching for product")
	@Description(" Searching a product")
	@Epic("EP001")
	@Story("UserStory001")
	@Feature("SearchItem feature")
	@Step("Item search")
	@Severity(SeverityLevel.NORMAL)
	public void SearchItem() {
		Driver.navigate().refresh();
		AmazonOR.SetCategoryList("Books");
		AmazonOR.setSearchInput("Da Vinci Code");
		AmazonOR.setMagnifierBtn();
		Assert.assertTrue(Driver.getTitle().contains("Da Vinci Code"));
		
	}
	
	@Test(priority=1)
	@Description(" Verify Footer links")
	@Epic("EP001")
	@Story("UserStory001")
	@Feature("UI")
	@Step("UI check")
	@Severity(SeverityLevel.MINOR)
	public void getFooterlinks() {
		
		List<WebElement> allFooterLinks = AmazonOR.getAllFooterLinks();
		
		int size = allFooterLinks.size();
		
		Assert.assertTrue(size>=60);
	}

}
