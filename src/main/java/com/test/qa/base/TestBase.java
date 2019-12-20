package com.test.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.test.qa.util.OsUtils;
import com.test.qa.util.TestUtil;
import com.test.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	private static String driverDir = System.getProperty("user.dir") + "/drivers/chromedriver";

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			if (OsUtils.isUnix()) {
				System.setProperty("webdriver.chrome.driver", driverDir);
				driver = new ChromeDriver();
			}
			// not implemeted for mac
			else {
				System.setProperty("webdriver.chrome.driver", driverDir + ".exe");
				driver = new ChromeDriver();
			}

		}

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

}
