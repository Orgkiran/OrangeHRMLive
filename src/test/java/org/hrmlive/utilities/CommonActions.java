package org.hrmlive.utilities;

import org.hrmlive.testlisteners.LocalListeners;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class CommonActions {
	static ExtentTest test = LocalListeners.getTest();
	static WebDriver driver;

	public static void clickOnElement(WebElement ele, String stepmessage) {
		ele.click();

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			test.log(Status.WARNING, "Element not found on the requested page\n " + ele.getAccessibleName(),
					MediaEntityBuilder
							.createScreenCaptureFromBase64String(captureScreenshot(LocalListeners.getDriver()))
							.build());
			Reporter.log("Element not found: " + ele.getAccessibleName());
		}

	}

	public static void addTOReport(String message) {
		test.log(Status.PASS, message, MediaEntityBuilder
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