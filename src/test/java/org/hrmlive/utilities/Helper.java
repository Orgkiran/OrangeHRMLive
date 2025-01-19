package org.hrmlive.utilities;

import java.time.LocalDate;
import java.util.UUID;

import org.hrmlive.pages.HomePage;
import org.hrmlive.pages.LoginPage;
import org.hrmlive.testlisteners.LocalListeners;

import com.github.javafaker.Faker;

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
		
		if(loginpage.getUsernamefield().isDisplayed()) {
		CommonActions.setText(loginpage.getUsernamefield(), username, "Entered username successfully");
		CommonActions.setText(loginpage.getPasswordfield(), password, "Entered Password successfully");
		CommonActions.clickOnElement(loginpage.getLoginButton(), "Clicked on Login button");

		HomePage home = new HomePage(LocalListeners.getDriver());
		CommonActions.verifyElementExists(home.getDashBoardHeader(), "Logged in Successfully");
		}
	}

	public static void logout() {
		HomePage home = new HomePage(LocalListeners.getDriver());
		CommonActions.clickOnElement(home.getProfileIcon(), "Clicked on Profile icon");
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
	
	public static String getRandomFirstName() {
		Faker fake = new Faker();
		String fname = fake.name().firstName();
		return fname;
	}
	
	public static String getRandomLastName() {
		Faker fake = new Faker();
		String lname = fake.name().lastName();
		return lname;
	}
	
	public static String getRandomFullName() {
		Faker fake = new Faker();
		String fullname = fake.name().fullName();
		return fullname;
	}
	
	public static String getRandomPhoneNumber() {
		Faker fake = new Faker();
		String phoneNumber = "9" + fake.number().digits(9);
		return phoneNumber;
	}
	
	public static String getRandomPassword() {
		String password = UUID.randomUUID().toString();
		return password;
	}

}
