package org.hrmlive.testlisteners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.hrmlive.utilities.CommonActions;
import org.hrmlive.utilities.Helper;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalListeners implements ITestListener {

	private static WebDriver driver;
	private static ExtentReports extent;
	private static ExtentSparkReporter spark;
	private static ExtentTest test;
	private static Properties prop;
	final File CONF = new File("./src/test/java/resources/extent-config.xml");

	public static ExtentTest getTest() {
		return test;
	}

	public static Properties getProp() {
		return prop;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static ExtentReports getExtent() {
		return extent;
	}

	public void onStart(ITestContext context) {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					new File("C:/Users/kiran/OrangeHrmLive/OrangeHRMLive/src/test/java/resources/config.properties"));
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		extent = new ExtentReports();

		spark = new ExtentSparkReporter(
				new File("./reports/" + getCurrentDate() + "/OrangeHRM_" + getCurrentDateWithTime() + ".html"));
		try {
			spark.loadXMLConfig(CONF);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extent.attachReporter(spark);
		driver.get(prop.getProperty("url"));
	}

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		Dimension screenSize = driver.manage().window().getSize();
		((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
		test.log(Status.INFO, "Browser: " + prop.getProperty("browser").toUpperCase() + ", and Screen Size: "
				+ screenSize.toString());
		Reporter.log("Test Started: " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Pass", MediaEntityBuilder
				.createScreenCaptureFromBase64String(CommonActions.captureScreenshot(driver)).build());
		Reporter.log("Test Passed: " + result.getName());
		System.out.println("Test Passed: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		Throwable var = result.getThrowable();
		test.log(Status.FAIL, "STEP FAILED: \n" + var, MediaEntityBuilder
				.createScreenCaptureFromBase64String(CommonActions.captureScreenshot(driver)).build());
		Reporter.log("Test Failed: " + result.getName());
		System.out.println("Test Failed: " + result.getName());
		Helper.logout();
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Skipped at " + getCurrentDateWithTime());
		Reporter.log("Test Skipped: " + result.getName());
		System.out.println("Test Skipped: " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Throwable throwVar = result.getThrowable();
		test.log(Status.FAIL, "Test Failed at " + getCurrentDateWithTime() + "\n" + throwVar
				+ MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(getDriver())).build());
		System.out.println("Test Failed: " + result.getName());
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
		Throwable throwVar = result.getThrowable();
		test.log(Status.FAIL, "Test Failed at " + getCurrentDateWithTime() + "\n" + throwVar
				+ MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(getDriver())).build());
		System.out.println("Test Failed: " + result.getName());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		driver.quit();
	}

	public static String getCurrentDate() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		String currentDate = formatter.format(date);
		return currentDate;
	}

	public static String getCurrentDateWithTime() {
		ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		// Format the time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy_HH-mm-ss");
		String formattedTime = istTime.format(formatter);
		return formattedTime;
	}

	public static String captureScreenshot(WebDriver d) {
		TakesScreenshot screenshot = (TakesScreenshot) d;
		String base64code = screenshot.getScreenshotAs(OutputType.BASE64);
		return base64code;
	}

}
