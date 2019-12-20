package com.test.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.qa.base.TestBase;

public class LoginPage extends TestBase{
	

	
	@FindBy(xpath="//*[contains(text(),'✕')]")
	WebElement closeLoginBtn;
	
	@FindBy(xpath="//input[@type='text']")
	WebElement searchBox;
	
	@FindBy(xpath="//input[@type='text']/../following-sibling::button")
	WebElement searchButton;
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public void closeLoginPopup(){
		closeLoginBtn.click();
	}
	
	public void search(String searchItem){
		searchBox.sendKeys(searchItem);
		searchButton.click();
	}
	
}
