package com.qa.cgi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RediffPages {
	
	WebDriver Driver;
	
	/*1. Get the locator and store it in the reference variable
	2. Define the actions that to be performed on the elements*/
	
	/*Syntax:
		
		@FindBy(locator="value1")
		@CacheLookup
		WebElement ReferenceName;*/
	
	// Create account link
	
	@FindBy(linkText="Create Account")
	@CacheLookup
	WebElement NewRegistrationLink;
	
	public String getNewRegistrationLink() {
		return NewRegistrationLink.getAttribute("href");
	}
	
	@FindBy(name="proceed")
	WebElement SigInButton;
	
	public void setSigInButtonClick() {
		SigInButton.click();
	}


	public void setNewRegistrationLinkClick() {
		NewRegistrationLink.click();
	}
	
	@FindBy(linkText="privacy policy")
	WebElement PrivacyPoliciyLink;
	
	public void setPrivacyPoliciyLinkClick() {
		PrivacyPoliciyLink.click();
	}
	

	
	@FindBy(linkText="Sign in")
	WebElement SiginLink;
	
	
	public void setSiginLinkClick() {
		SiginLink.click();
	}
	
	@FindBy(linkText="terms and conditions")
	WebElement TermandConditionsLink;
	
	public void setTermandConditionsLink() {
		TermandConditionsLink.click();
	}


	public RediffPages(WebDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	
	
	
	
}
