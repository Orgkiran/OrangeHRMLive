package org.hrmlive.utilities;

import java.time.LocalDate;

import org.hrmlive.pages.HomePage;
import org.hrmlive.pages.LoginPage;
import org.hrmlive.testlisteners.LocalListeners;

public class Helper {

	public static String getCurrentDate() {
		LocalDate date = LocalDate.now();
		String currentDate = date.toString();
		return currentDate;
	}

	public static void login() {
		String username = LocalListeners.getProp().getProperty("username");
		String password = LocalListeners.getProp().getProperty("password");

		LoginPage loginpage = new LoginPage(LocalListeners.getDriver());
		CommonActions.setText(loginpage.getUsernamefield(), username, "Entered username successfully");
		CommonActions.setText(loginpage.getPasswordfield(), password, "Entered Password successfully");
		CommonActions.clickOnElement(loginpage.getLoginButton(), "Clicked on Login button");

		HomePage home = new HomePage(LocalListeners.getDriver());
		CommonActions.verifyElementExists(home.getDashBoardHeader(), "Logged in Successfully");
	}

	public static void logout() {
		HomePage home = new HomePage(LocalListeners.getDriver());
		CommonActions.clickOnElement(home.getProfileIcon(), "Clicked on Peofile icon");
		CommonActions.clickOnElement(home.getLogoutButton(), "Clicked on Logout Button");

		LoginPage login = new LoginPage(LocalListeners.getDriver());
		CommonActions.verifyElementExists(login.getUsernamefield(), "Logged out Successfully");
	}

	public static void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
