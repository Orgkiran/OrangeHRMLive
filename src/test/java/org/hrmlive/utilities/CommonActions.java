package org.hrmlive.utilities;

import org.hrmlive.testlisteners.LocalListeners;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class CommonActions {
	static WebDriver driver;

	public static void clickOnElement(WebElement ele, String stepmessage) {
		ele.click();

		Helper.waitForSeconds(1);

		addTOReport(stepmessage);
	}

	public static void setText(WebElement ele, String testdata, String message) {
		ele.sendKeys(testdata);
		addTOReport(message);
	}

	public static void verifyElementExists(WebElement ele, String message) {

		try {
			ele.isDisplayed();
			addTOReport(message);
		} catch (Exception e) {
			LocalListeners.getTest().log(Status.WARNING, "Element not found on the requested page\n " + ele.getAccessibleName(),
					MediaEntityBuilder
					.createScreenCaptureFromBase64String(captureScreenshot(LocalListeners.getDriver()))
					.build());
			Reporter.log("Element not found: " + ele.getAccessibleName());
		}

	}

	//To verify full text equals to actual String
	public static void verifyTextEquals(WebElement ele, String expected,String stepdescription) {
		String actual = ele.getText();

		try {
			Assert.assertEquals(actual, expected);
			LocalListeners.getTest().log(Status.PASS, stepdescription, MediaEntityBuilder
					.createScreenCaptureFromBase64String(captureScreenshot(LocalListeners.getDriver())).build());
			Reporter.log("Step: " + stepdescription);
		}catch(AssertionError e) {
			LocalListeners.getTest().log(Status.WARNING, "Expected: \'" +expected+  "\' is not matching with Actual: \'"+actual+"\'",
					MediaEntityBuilder
					.createScreenCaptureFromBase64String(captureScreenshot(LocalListeners.getDriver()))
					.build());
			Reporter.log("Expected: \'" +expected+  "\' is not matching with Actual: \'"+actual+"\'");
		}
	}
	
	//To verify partial text is matching Actual String
	public static void verifyTextContains(WebElement ele, String expected,String stepdescription) {
		String actual = ele.getText();

		try {
			String act = actual.toLowerCase();
			boolean result = act.contains(expected.toLowerCase());
			Assert.assertTrue(result);
			LocalListeners.getTest().log(Status.PASS, stepdescription, MediaEntityBuilder
					.createScreenCaptureFromBase64String(captureScreenshot(LocalListeners.getDriver())).build());
			Reporter.log("Step: " + stepdescription);
		}catch(AssertionError e) {
			LocalListeners.getTest().log(Status.WARNING, "Expected: \'" +expected+  "\' is not present in Actual: \'"+actual+"\'",
					MediaEntityBuilder
					.createScreenCaptureFromBase64String(captureScreenshot(LocalListeners.getDriver()))
					.build());
			Reporter.log("Expected: \'" +expected+  "\' is not matching with Actual: \'"+actual+"\'");
		}
	}

	public static void addTOReport(String message) {
		LocalListeners.getTest().log(Status.PASS, message, MediaEntityBuilder
				.createScreenCaptureFromBase64String(captureScreenshot(LocalListeners.getDriver())).build());
		Reporter.log("Step: " + message);
	}

	public static String captureScreenshot(WebDriver d) {
		driver = d;
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String base64code = screenshot.getScreenshotAs(OutputType.BASE64);
		return base64code;
	}

}
