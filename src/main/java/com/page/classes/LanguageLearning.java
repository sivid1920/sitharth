package com.page.classes;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.classes.BaseClass;

public class LanguageLearning extends BaseClass {

	@FindBy(xpath = "//*[@id=\"react-select-2--value\"]/div[1]")
	public static WebElement Language;

	@FindBy(xpath = ("//div[@class='checkboxText']"))
	public List<WebElement> languageCheck;

	@FindBy(xpath = "//button[contains(text(),'Show All')]")
	public static WebElement showAll;

	@FindBy(xpath = "//div[@class='checkboxText']")//*[@id=\"main\"]/div/div[2]/div/div[2]/div[1]/div/div[3]/div/div[2]/div[2]")
	public static List<WebElement> fetch_All;

	@FindBy(xpath = "//a[@class='nostyle']")
	public static WebElement closeLanguage;

	@FindBy(xpath = "//div[@id='react-select-3--value']")
	public static WebElement Level;

	@FindBy(xpath = ("//span[@class='_bc4egv p-x-1s font-weight-normal']"))
	public List<WebElement> levelCheck;

	String screenshot = "LanguageLearningshot";

	public LanguageLearning(WebDriver l_driver, ExtentTest logger) {

		super(l_driver, logger);
		PageFactory.initElements(driver, this);

	}

	public void selectLanguage() {

		// Extract all the language with its total count
		logger.log(Status.INFO, "Clicking the language field");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(Language)).click();
		reportPass("Language field is clicked");

		logger.log(Status.INFO, "Clicking the showall link in the language field");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		showAll.click();
		takeScreenShot(screenshot);
		reportPass("Show all link in the language field is clicked successfully");
		languageCheck = fetch_All;
		//String all = fetch_All.getText();
		//System.out.println(all);
		for(WebElement Element: languageCheck)
		{
			//System.out.println(Element.getText());
			Reporter.log(Element.getText(), true);
		}
		reportPass("Elements of language field is displayed");
		closeLanguage.click();

	}

	public void selectLevel() {

		// Level field is selected
		logger.log(Status.INFO, "Clicking the level field");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Level.click();
		reportPass("Level field is clicked");
		takeScreenShot(screenshot);

		// Extract all the levels with its total count
		for (WebElement lang2 : levelCheck) {
			String levels = lang2.getText();
			System.out.println(levels);
		}
		reportPass("Elements of level field is displayed");
		driver.close();
	}

}
