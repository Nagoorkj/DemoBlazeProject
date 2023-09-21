package com.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import com.base.BaseClass;
import com.pom.DemoBlazeSite;
import com.utils.Util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

public class StepDefinitions {

	private WebDriver driver;

	BaseClass base;
	Util util;
	
	//reading from excel sheet 

	String browsername = util.getDataFromExcel("Config", "Browser");
	String url = util.getDataFromExcel("Config", "Url");

	String ExpectedUserName = "";
	String AlertMessage = "";

	@Before
	public void setup() {

		base = new BaseClass();
		base.ChooseDriver(browsername, url);
		driver = base.getDriver();
		DemoBlazeSite login = new DemoBlazeSite(driver);
		util = new Util();

	}

	@After
	public void TearDown() {

	 base.exitCurrentBrowser();

	}

	// Login login = new Login(driver);

	@Given("launching the homepage")
	public void launching_the_homepage() {
		// launch the url in chrome browser
		driver.navigate().to(url);
	}

//for sign up

	@Given("User Clicks  on the SignUp Button")
	public void user_clicks_on_the_sign_up_button() {

		DemoBlazeSite login = new DemoBlazeSite(driver);
		login.SignUp();

	}

	@When("I pass the {string} and {string}")
	public void i_pass_the_and(String username, String password) {
		// Login login = new Login(driver);
		try {
			

			int Unique=util.getUnique();
			DemoBlazeSite login = new DemoBlazeSite(driver);
			login.EnterCredentials(username+Unique, password);

			// util.WaitingThread(4000);
			AlertMessage = util.isAlertPresent(driver);
			System.out.println("alertMessage is " + AlertMessage);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("User should be create the account successfully.")
	public void user_should_be_create_the_account_successfully() {
		// Validate the sign up

		String ExpectedMessage = "Sign up successful.";

		// System.out.println("Actual message " + AlertMessage);
		assertTrue(ExpectedMessage.equals(AlertMessage),
				"User ID is not created please check the details" + AlertMessage);
	}

	@When("User should be landed to the HomePage")
	public void user_should_be_landed_to_the_home_page() {
		// Write code here that turns the phrase above into concrete actions
		DemoBlazeSite login = new DemoBlazeSite(driver);
	
		WebElement button=login.getSignUp();
		boolean status=button.isEnabled();		
		System.out.println("Signup Button should be displayed"+status);		
		assertTrue(status);
		
		String ActualSiteName=login.HomePageValidation();
		
		String ExpectedSiteName =util.getDataFromExcel("Config", "SiteName");
		
		System.out.println("Expected Site" + ActualSiteName);
		assertTrue(ExpectedSiteName.equals(ActualSiteName), "site is not matching");
		
		
		
		
		
	}

	// for login

	@Given("Verify the site is working properly")
	public void verify_the_site_is_working_properly() {
		// new Login(driver);
		// Write code here that turns the phrase above into concrete actions
		String currentURL = driver.getCurrentUrl();
		String ExpectedURL = url;

		System.out.println("Current URL" + currentURL);
		assertTrue(ExpectedURL.equals(currentURL), "site is not expected");

	}

	@Given("User Clicks  on the Login Button")
	public void user_clicks_on_the_login_button() {
		DemoBlazeSite login = new DemoBlazeSite(driver);
		login.ClickLogin();

	}

	@When("I enter {string} and {string}")
	public void i_enter_and(String username, String password) throws InterruptedException {

		DemoBlazeSite login = new DemoBlazeSite(driver);
		//when I pass data from excel
		
		
		login.loginWithCredentials(util.getDataFromExcel("login", username), util.getDataFromExcel("login", password));
		ExpectedUserName = util.getDataFromExcel("login", username);
		
	}



	@Then("User should be logged in and greeting message should be displayed")
	public void verify_the_welcome_message() throws InterruptedException {
		DemoBlazeSite login = new DemoBlazeSite(driver);

		String Actual = login.GetUserName();
		
		//String name="Welcome meeran famiya";
		String splitName="Welcome";
		
		String[] output=Actual.split(splitName);
		
		String ActualUserName=output[1].toString().trim();
		
		System.out.println("output is "+output[1].toString());
		
//		System.out.println("excel data has taken  "+ExpectedUserName);
//
//		 System.out.println("Expected user " + ExpectedUserName);
//		 System.out.println("current user " + Actual);
		 assertTrue(ExpectedUserName.equalsIgnoreCase(ActualUserName), "User Name is not is Matching");

	}

}
