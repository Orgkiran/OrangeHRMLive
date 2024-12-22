package org.hrmlive.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {
	private WebDriver driver;

	public AdminPage(WebDriver d) {
		driver = d;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()=' Add ']")
	private WebElement addUserButton;

	public WebElement getAddUserButton() {
		return addUserButton;
	}
	
}
