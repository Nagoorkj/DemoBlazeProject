package com.base;

import java.time.Duration;
import java.util.Timer;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.WebElement;




public class BaseClass {

	protected WebDriver driver;

	public void ChooseDriver(String Driver, String Url) {

		if (Driver.equalsIgnoreCase("Chrome")) {

			driver = new ChromeDriver();

			driver.get(Url);
			driver.manage().window().maximize();
			System.out.println("am opening chrome");

		}

		if (Driver.equalsIgnoreCase("Firefox")) {

			driver = new FirefoxDriver();

			driver.get(Url);
			driver.manage().window().maximize();
			System.out.println("am opening firfox");

		}
		if (Driver.equalsIgnoreCase("Safari")) {

			driver = new SafariDriver();

			driver.get(Url);
			driver.manage().window().maximize();
			System.out.println("am opening safari");

		} else {
			System.err.println("please choose driver name like chrome, firefox or safari");
		}
		//return driver;

	}

	public WebDriver getDriver() {
		return this.driver;

	}

	public void exitAllBrowser() {
			driver.close();

	}
	
	public String getText(WebElement webelement) {
		String GetText=webelement.getText();
		
		return GetText;
		

	}
	
	
	
	
	
	

	
	

	
	
	public void exitCurrentBrowser() {
		driver.quit();

	}

}
