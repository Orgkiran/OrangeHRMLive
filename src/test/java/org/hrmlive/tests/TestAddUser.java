package org.hrmlive.tests;

import org.hrmlive.pages.AddUserPage;
import org.hrmlive.pages.AdminPage;
import org.hrmlive.pages.HomePage;
import org.hrmlive.testlisteners.LocalListeners;
import org.hrmlive.utilities.CommonActions;
import org.hrmlive.utilities.Helper;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LocalListeners.class)
public class TestAddUser {
	
	@Test(description = "Verifying the elements are displayed on the Add user screen" , groups = "Regression")
	public static void verifyAddUserPage() {
		
		Helper.login();
		
		HomePage home = new HomePage(LocalListeners.getDriver());
		CommonActions.verifyElementExists(home.getAdminLink(), "Verify Admin link Present on Home page");
		CommonActions.clickOnElement(home.getAdminLink(), "Click on Admin link");
		
		AdminPage admin = new AdminPage(LocalListeners.getDriver());
		CommonActions.clickOnElement(admin.getAddUserButton(), "Click on Add button");
		
		AddUserPage adduser = new AddUserPage(LocalListeners.getDriver());
		CommonActions.verifyElementExists(adduser.getAddUserHeader(), "Verify Add user header present on the screen");
		CommonActions.verifyTextEquals(adduser.getAddUserHeader(), "Add users", "Verify the header text displayed as expected");
		CommonActions.verifyTextContains(adduser.getAddUserHeader(), "Adds", "Verify the header contains expected String");
		CommonActions.verifyElementExists(adduser.getUserRoleDropdown(), "Verify user role dropdown present on the screen");
		CommonActions.verifyElementExists(adduser.getEmployeeName(), "Verify Employee name field present on the screen");
		CommonActions.verifyElementExists(adduser.getUsernametextfield(), "Verify Username text field present on the screen");
		CommonActions.verifyElementExists(adduser.getPasswordfield(), "Verify Password text field present on the screen");
		CommonActions.verifyElementExists(adduser.getConfirmpasswordfield(), "Verify Confirm password text field present on the screen");
		CommonActions.verifyElementExists(adduser.getStatusDropdown(), "Verify Status dropdown present on the screen");
		CommonActions.verifyElementExists(adduser.getCancelButton(), "Verify Cancel button present on the screen");
		CommonActions.verifyElementExists(adduser.getSaveButton(), "Verify Save button present on the screen");
	
		Helper.logout();
	}

}
