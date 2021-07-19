package com.test.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.classes.BaseClass;
import com.base.classes.GetProperties;
import com.framework.utilities.ExtentReportManager;
import com.page.classes.HomePage;
import com.page.classes.LanguageLearning;
import com.page.classes.SearchCourse;

public class LanguageLearningTest {

	public static WebDriver driver;
	public static WebDriverWait wait;
	// Extent report
	public static ExtentReports report;
	public static ExtentTest logger;

	BaseClass baseclass;
	HomePage homepage;
	SearchCourse searchcourse;
	LanguageLearning languagelearning;

	@BeforeMethod(groups = { "Regression" })
	public void InvokeBrowser() {

		// The browser name is got from the properties file
		// Report and logger is instantiated
		String browser = GetProperties.getBrowser1();
		String Testname = "LanguageLearningTestReport";
		report = ExtentReportManager.getReportInstance(browser, Testname);
		logger = report.createTest("Language Learning");

		baseclass = new BaseClass(driver, logger);

		boolean isvalid = baseclass.validateBrowser(browser);
		Assert.assertTrue(isvalid, browser + " is valid");

		// All the page classes are instantiated
		logger.log(Status.INFO, "Opened the browser ");
		driver = baseclass.openBrowser(browser);
		logger.log(Status.PASS, "Successfully opened the " + browser + " browser");
		homepage = new HomePage(driver, logger);
		searchcourse = new SearchCourse(driver, logger);
		languagelearning = new LanguageLearning(driver, logger);
		baseclass.getUrl();

	}

	@Test(priority = 1, groups = { "Regression" })
	public void identifycourse() throws Exception {

		// Methods of the filling form class is called using the object of the class
		homepage.home("language learning");
		languagelearning.selectLanguage();
		languagelearning.selectLevel();

	}

	@AfterMethod(groups = { "Regression" })
	public void endTest() throws Exception {

		// Closing the browser after the test implementation
		logger.log(Status.INFO, "Closing the browser");
		baseclass.closeBrowser();
		logger.log(Status.PASS, "Closed browser successfully");
		report.flush();

	}

}
