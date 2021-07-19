package com.base.classes;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.utilities.DateUtil;

public class BaseClass extends OpenBrowser {

	public static ExtentTest logger;
	public static WebDriverWait wait;
	public static String BaseUrl = "https://www.coursera.org/in";

	@SuppressWarnings("static-access")
	public BaseClass(WebDriver l_driver, ExtentTest logger) {
		this.driver = l_driver;
		this.logger = logger;
		PageFactory.initElements(l_driver, this);
	}

	/******************* validate browser name *********************/
	public boolean validateBrowser(String browsername) {
		if (browsername.equalsIgnoreCase("chrome")) {
			return true;
		} else if (browsername.equalsIgnoreCase("IE")) {
			return true;
		} else if (browsername.equalsIgnoreCase("firefox")) {
			return true;
		}
		return false;
	}

	/************** Navigating to the WebPage(coursera.com) ***************/
	public void getUrl() {
		try {
			logger.log(Status.INFO, "Getting the URL from the String BaseUrl");
			driver.get(BaseUrl);
			logger.log(Status.PASS, "Navigated to coursera.com");
			waitFunc();

		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage(), "URL Error");

		}
		
	}

	/******************* Take Screenshot ************************/
	public void takeScreenShot(String Screenshotname) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Copy the file to a location and use try catch block to handle exception
		try {
			String Screenshot = Screenshotname + " " + DateUtil.getDate() + ".png";
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshots\\" + Screenshot));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/****************** Accept Java Script Alert ***********************/
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			logger.log(Status.PASS, "Page Alert Accepted");
		} catch (Exception e) {
			reportFail(e.getMessage(), "AcceptAlert");
		}

	}

	/****************** Cancel Java Script Alert ***********************/
	public void cancelAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			;
			logger.log(Status.PASS, "Page Alert Rejected");
		} catch (Exception e) {
			reportFail(e.getMessage(), "CancelAlert");
		}

	}

	/****************** Select value From DropDown ***********************/
	public void selectDropDownValue(WebElement webElement, String value) {
		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(value);
			logger.log(Status.PASS, "Selectd the Value : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage(), "Error Dropdown");
		}
	}

	/****************** Verify Element is Present ***********************/
	public void veriyElementIsDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				reportPass("Element is Displayed");
			}

		} catch (Exception e) {
			reportFail(e.getMessage(), "Element not found");
		}

	}

	/************** To log the pass status into Report ***************/
	public void reportPass(String comments) {
		logger.log(Status.PASS, comments);
		Assert.assertTrue(true);
	}

	/*************** To log the pass status into Report ***************/
	public void reportFail(String comments, String Screenshot) {
		takeScreenShot(Screenshot);
		logger.log(Status.FAIL, comments);
		Assert.assertTrue(true, "Failing the Test after capturing the error!");

	}

	/************** Wait function **********************/
	public void waitFunc() throws InterruptedException {
		@SuppressWarnings("unused")
		WebDriverWait wait = new WebDriverWait(driver, 50);
	}

	/************* closes the browser *****************/
	public void closeBrowser() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		driver.quit();
		
			
	}

}
