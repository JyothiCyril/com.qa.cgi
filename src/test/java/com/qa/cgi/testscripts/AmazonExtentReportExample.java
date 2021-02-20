package com.qa.cgi.testscripts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.cgi.pages.AmazonPages;



public class AmazonExtentReportExample {
	
	WebDriver Driver;
	AmazonPages AmazonOR;
	ExtentReports XReport; 
	ExtentHtmlReporter HtmlReporter;
	ExtentTest XTest;
	
	@BeforeTest
	public void StartTest() {
		
		
		String property = System.getProperty("user.dir");
		
		System.out.println(property);
		

		HtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
		HtmlReporter.config().setDocumentTitle("Automation Report");
		HtmlReporter.config().setReportName("Functional Report");
		HtmlReporter.config().setTheme(Theme.DARK);
		
		XReport = new ExtentReports();
		XReport.attachReporter(HtmlReporter);
		XReport.setSystemInfo("HostName", "LocalHost");
		XReport.setSystemInfo("OS", "Windows 10");
		XReport.setSystemInfo("Browser", "Chrome");
		XReport.setSystemInfo("Testername", "Kim Smith");
		
		
	}
	
	
	@AfterTest
	public void endTest() {
		XReport.flush();
	}
	
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTraining\\LTISeleniumTrainingBatch1\\Drivers\\chromedriver.exe");
		Driver = new ChromeDriver();
		AmazonOR = new AmazonPages(Driver);
		Driver.get("https://www.amazon.in/");
	}
	
	@AfterMethod
	public void tearDown(ITestResult results) {
		
		
		if(results.getStatus() == ITestResult.FAILURE) {
			XTest.log(Status.FAIL, "Test failed is : "  + results.getName());
			XTest.log(Status.FAIL, "Test failed is : "  + results.getThrowable());
		}else if(results.getStatus() == ITestResult.SUCCESS) {
			XTest.log(Status.PASS, "Test passed is : "  + results.getName());
			
		}else if(results.getStatus() == ITestResult.SKIP) {
			XTest.log(Status.SKIP, "Test Skipped is : "  + results.getName());
			
		}
		
		
		
		Driver.quit();
	}
	
	
	@Test(priority=0)
	public void CheckTitle() {
		XTest = XReport.createTest("CheckTitle"); // make an entry into the extent report
		
		String Title = Driver.getTitle();
		
		Assert.assertEquals(Title, "Welcome to Amazon");
		
		Reporter.log(Title);
		
	}
	
	@Test(priority=4)
	public void SearchItem() {
		XTest = XReport.createTest("SearchItem");
		
		AmazonOR.SetCategoryList("Books");
		AmazonOR.setSearchInput("Da vinci code");
		AmazonOR.setMagnifierBtn();		
		
		
	}
	
	
	@Test(priority=2, groups= "Hyperlinks")
	
	public void FooterLinks() {
		XTest = XReport.createTest("FooterLinks");
		
		List<WebElement> AllFooterLinks = AmazonOR.getAllFooterLinks();
		
		//int size = AllFooterLinks.size();
		
		
		for(WebElement link: AllFooterLinks) {
			
			Reporter.log(link.getText());
		}
		
	}
	

}
