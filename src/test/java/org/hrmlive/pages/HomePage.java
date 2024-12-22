package org.hrmlive.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver driver;

	public HomePage(WebDriver d) {
		driver = d;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[@class=\"oxd-userdropdown-name\"]")
	private WebElement profileIcon;

	@FindBy(xpath = "//a[@class=\"oxd-userdropdown-link\" and text()='Logout']")
	private WebElement logoutButton;

	@FindBy(xpath = "//h6[@class=\"oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module\"]")
	private WebElement dashBoardHeader;
	
	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement adminLink;

	public WebElement getAdminLink() {
		return adminLink;
	}

	public WebElement getDashBoardHeader() {
		return dashBoardHeader;
	}

	public WebElement getProfileIcon() {
		return profileIcon;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}

}
