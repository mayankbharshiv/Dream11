package com.test.qa.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.qa.base.TestBase;
import com.test.qa.util.SortModels;

public class SearchPage extends TestBase {
	WebDriverWait wait = new WebDriverWait(driver, 5);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Actions action = new Actions(driver);

	@FindBy(xpath = "//*[@title='Mobiles & Accessories']/../../following-sibling::div")
	WebElement category;

	@FindBy(xpath = "//input[@type='checkbox']//following-sibling::div[contains(text(),'Apple')]/preceding-sibling::div")
	WebElement brand;

	@FindBy(xpath = "//div[@class='_1qKb_B']/select[@class='fPjUPw']")
	WebElement minPriceDropDown;

	@FindBy(xpath = "//div[@class='_1YoBfV']/select[@class='fPjUPw']")
	WebElement maxPriceDropDown;

	@FindBy(xpath = "//*[contains(text(),'Availability')]")
	WebElement availabilityPane;

	@FindBy(xpath = "//*[@type='checkbox']/following-sibling::div[contains(text(),'Exclude Out of Stock')]")
	WebElement availabilitySelection;

	@FindBy(xpath = "//*[contains(text(),'Filters')]/../../following-sibling::div/div/div/div")
	List<WebElement> filterApplied;

	@FindBy(xpath = "//*[@class='_31qSD5']")
	List<WebElement> allResultWebElement;

	@FindBy(xpath = "//*[@class='_31qSD5']/div[@class='_1-2Iqu row']/div[@class='col col-5-12 _2o7WAb']//div[@class='_1uv9Cb']/div[@class='_1vC4OE _2rQ-NK']")
	List<WebElement> modelsPrice;

	@FindBy(xpath = "//*[@class='_31qSD5']/div[@class='_1-2Iqu row']/div[@class='col col-7-12']/div[@class='_3wU53n']")
	List<WebElement> modelsName;

	// Initializing the Page Objects:
	public SearchPage() {
		PageFactory.initElements(driver, this);

	}

	public void selectCategory() {
		wait.until(ExpectedConditions.elementToBeClickable(category));
		category.click();
	}

	public void selectBrand() {
		wait.until(ExpectedConditions.elementToBeClickable(brand));
		action.moveToElement(brand).build().perform();
		brand.click();
	}

	public void selectMinPriceDown(String minPrice) {
		wait.until(ExpectedConditions.elementToBeClickable(minPriceDropDown));
		Select select = new Select(minPriceDropDown);
		select.selectByValue(minPrice);
	}

	public void selectMaxPriceDown(String maxPrice) {
		wait.until(ExpectedConditions.elementToBeClickable(maxPriceDropDown));
		Select select = new Select(maxPriceDropDown);
		select.selectByValue(maxPrice);
	}

	public void selectAvailability() {
		wait.until(ExpectedConditions.elementToBeClickable(availabilityPane));
		action.moveToElement(availabilityPane).build().perform();
		availabilityPane.click();
		wait.until(ExpectedConditions.elementToBeClickable(availabilitySelection));
		action.moveToElement(availabilitySelection).build().perform();
		availabilitySelection.click();
	}

	public boolean verifyFilterSelected(String brand, String availability) {
		driver.navigate().refresh();
		for (WebElement eleItem : filterApplied) {
			if (eleItem.getText().equals(brand) || eleItem.getText().equals(availability)) {
				return true;
			}

		}
		return false;
	}

	public void verifyResults() {
		ArrayList<SortModels> sortModels = new ArrayList<SortModels>();

		for (int i = 0; i < allResultWebElement.size(); i++) {
			sortModels.add(new SortModels(allResultWebElement.get(i).getAttribute("href"), modelsName.get(i).getText(),
					Integer.valueOf(modelsPrice.get(i).getText().substring(1).replace(",", ""))));
		}

		Collections.sort(sortModels);
		for (SortModels sortModel : sortModels) {
			System.out.println(sortModel.getName() + "|" + sortModel.getHref() + "|" + sortModel.getPrice());
		}

	}
}
