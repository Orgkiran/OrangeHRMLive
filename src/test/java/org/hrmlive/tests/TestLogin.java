package org.hrmlive.tests;

import org.hrmlive.testlisteners.LocalListeners;
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
}
