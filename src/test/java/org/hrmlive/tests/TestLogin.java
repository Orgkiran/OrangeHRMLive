package org.hrmlive.tests;

import org.hrmlive.pages.HomePage;
import org.hrmlive.pages.LoginPage;
import org.hrmlive.testlisteners.LocalListeners;
import org.hrmlive.utilities.CommonActions;
import org.hrmlive.utilities.Helper;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LocalListeners.class)
public class TestLogin {

	@Test(description = "To validate the Login and Logout Functionalities", groups = "regression")
	public static void loginWithValidCreds() {
		Helper.login();
		Helper.waitForSeconds(3);
		Helper.logout();
	}
	
	@Test(description = "To validate the Login with Invalid credentials", groups = "regression")
	public static void loginWithInvalidCredentials() {
		String username = LocalListeners.getProp().getProperty("username");
		String password = LocalListeners.getProp().getProperty("password");
		
		LoginPage login = new LoginPage(LocalListeners.getDriver());
		CommonActions.verifyElementExists(login.getUsernamefield(), "Verify Username field exists");
		CommonActions.verifyElementExists(login.getPasswordfield(), "Verify Password field exists");
		CommonActions.verifyElementExists(login.getLoginButton(), "Verify Login button exists");
		
		CommonActions.clickOnElement(login.getLoginButton(), "Click on login button");
		CommonActions.verifyTextEquals(login.getUsernamefieldError(), "Required", "Verify the error message for username field is displayed");
		CommonActions.verifyTextEquals(login.getPasswordfieldError(), "Required", "Verify the error message for password field is displayed");
		
		CommonActions.setText(login.getUsernamefield(), username, "Entered Username");
		CommonActions.clickOnElement(login.getLoginButton(), "Click on login button");
		CommonActions.verifyTextEquals(login.getPasswordfieldError(), "Required", "Verify the error message for password field is displayed");
		CommonActions.setText(login.getPasswordfield(), password, "Entered Password");
		CommonActions.clickOnElement(login.getLoginButton(), "Click on login button");
		
		HomePage home = new HomePage(LocalListeners.getDriver());
		CommonActions.verifyElementExists(home.getDashBoardHeader(), "Logged in Successfully");
		Helper.logout();
	}
}
