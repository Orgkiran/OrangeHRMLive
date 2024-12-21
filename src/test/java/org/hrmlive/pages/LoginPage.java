package org.hrmlive.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;

	public LoginPage(WebDriver d) {
		driver = d;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	private WebElement usernamefield;

	@FindBy(name = "password")
	private WebElement passwordfield;

	@FindBy(xpath = "//button[@type=\"submit\"]")
	private WebElement loginButton;

	public WebElement getUsernamefield() {
		return usernamefield;
	}

	public WebElement getPasswordfield() {
		return passwordfield;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

}
