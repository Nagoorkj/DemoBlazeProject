package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.utils.Util;

public class DemoBlazeSite extends Util {
	private WebDriver driver;

	// for sign up

	@CacheLookup
	@FindBy(id = "signin2")
	WebElement Signup;

	@CacheLookup
	@FindBy(id = "sign-username")
	WebElement susername;

	@CacheLookup
	@FindBy(id = "sign-password")
	WebElement spassword;

	@CacheLookup
	@FindBy(xpath = "//button[contains(text(),'Sign up')]")
	WebElement SignupButton;

	// for login
	@CacheLookup
	@FindBy(id = "login2")
	WebElement uilogin;

	@CacheLookup
	@FindBy(id = "loginusername")
	WebElement lusername;

	@CacheLookup
	@FindBy(id = "loginpassword")
	WebElement lpassword;

	@CacheLookup
	@FindBy(xpath = "//button[contains(text(),'Log')]")
	WebElement loginBtn;

	@CacheLookup
	@FindBy(xpath = "//a[@id='nameofuser']")
	WebElement welcome;

	@CacheLookup
	@FindBy(xpath = "//a[@id='nava']")
	WebElement HomePage;

	BaseClass bc = new BaseClass();

	public DemoBlazeSite(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	// for signup
	public void SignUp() {
		Signup.click();
	}

	public void EnterCredentials(String username, String password) throws InterruptedException {

		WebElement returnedElement = explicitWait(driver, susername);
		returnedElement.sendKeys(username);
		spassword.sendKeys(password);
		SignupButton.click();
	}

	// for login
	public void ClickLogin() {
		uilogin.click();
	}

	public void loginWithCredentials(String username, String password) throws InterruptedException {

		WebElement returnedElement = explicitWait(driver, lusername);
		returnedElement.sendKeys(username);
		lpassword.clear();
		lpassword.sendKeys(password);
		loginBtn.click();
	}

	public String GetUserName() throws InterruptedException {
		waitingThread(4000);
		welcome.isDisplayed();
		welcome.getText();
		System.out.println("Print " + welcome.getText());
		return welcome.getText();

	}

	public WebElement getSignUp() {
		return SignupButton;

	}

	public String HomePageValidation() {
		String HomePageText = HomePage.getText();
		return HomePageText;
	}

}
