package org.hrmlive.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddUserPage {
	private WebDriver driver;

	public AddUserPage(WebDriver d) {
		driver = d;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h6[text()='Add User']")
	private WebElement addUserHeader;

	@FindBy(xpath = "//label[text()='User Role']//parent::div/following-sibling::div//div[@class=\"oxd-select-text-input\"]")
	private WebElement userRoleDropdown;
	
	@FindBy(xpath = "//input[@placeholder=\"Type for hints...\"]")
	private WebElement employeeName;
	
	@FindBy(xpath = "//label[text()='Status']//parent::div/following-sibling::div//div[@class=\"oxd-select-text-input\"]")
	private WebElement statusDropdown;

	@FindBy(xpath = "//label[text()='Username']//parent::div/following-sibling::div//input[@class=\"oxd-input oxd-input--active\"]")
	private WebElement usernametextfield;
	
	@FindBy(xpath = "//label[text()='Password']//parent::div/following-sibling::div//input[@class=\"oxd-input oxd-input--active\"]")
	private WebElement passwordfield;
	
	@FindBy(xpath = "//label[text()='Confirm Password']//parent::div/following-sibling::div//input[@class=\"oxd-input oxd-input--active\"]")
	private WebElement confirmpasswordfield;
	
	@FindBy(xpath = "//button[@type='button' and text()= ' Cancel ']")
	private WebElement cancelButton;
	
	@FindBy(xpath = "//button[@type='submit' and text()= ' Save ']")
	private WebElement saveButton;

	public WebElement getAddUserHeader() {
		return addUserHeader;
	}

	public WebElement getUserRoleDropdown() {
		return userRoleDropdown;
	}

	public WebElement getEmployeeName() {
		return employeeName;
	}

	public WebElement getStatusDropdown() {
		return statusDropdown;
	}

	public WebElement getUsernametextfield() {
		return usernametextfield;
	}

	public WebElement getPasswordfield() {
		return passwordfield;
	}

	public WebElement getConfirmpasswordfield() {
		return confirmpasswordfield;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
}
