package com.test.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.test.qa.base.TestBase;
import com.test.qa.pages.LoginPage;
import com.test.qa.pages.SearchPage;

public class FlipkartTest extends TestBase {
	LoginPage loginPage;
	SearchPage searchPage;

	public FlipkartTest() {
		super();
	}

	@BeforeSuite
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		searchPage = new SearchPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		loginPage.closeLoginPopup();
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}

	@Test(priority = 2)
	public void enterSearchItem() {
		loginPage.search(prop.getProperty("searchItem"));
		Assert.assertTrue(driver.getPageSource().contains(prop.getProperty("searchItem")));
	}

	@Test(priority = 3)
	public void applyFilters() {
		searchPage.selectCategory();
		searchPage.selectMinPriceDown(prop.getProperty("minPrice"));
		searchPage.selectMaxPriceDown(prop.getProperty("maxPrice"));
		searchPage.selectBrand();
		searchPage.selectAvailability();
		Assert.assertTrue(searchPage.verifyFilterSelected(prop.getProperty("brand"), prop.getProperty("availability")));
	}
	
	@Test(priority = 4)
	public void displayResults() {
		searchPage.verifyResults();
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
