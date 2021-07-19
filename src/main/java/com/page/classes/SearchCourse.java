package com.page.classes;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.classes.BaseClass;

public class SearchCourse extends BaseClass {

	@FindBy(xpath = "//*[@id=\"react-select-2--value\"]/div[1]")
	public static WebElement Language;

	@FindBy(xpath = "//*[@id=\"react-select-2--option-0\"]/div/button/label/input")
	public static WebElement English;

	@FindBy(xpath = ("//div[@class='checkboxText']"))
	public List<WebElement> languageCheck;

	@FindBy(xpath = "//div[@id='react-select-3--value']")
	public static WebElement Level;

	@FindBy(xpath = ("//span[@class='_bc4egv p-x-1s font-weight-normal']"))
	public List<WebElement> levelCheck;

	@FindBy(xpath = "//input[@value='Beginner']")
	public static WebElement Beginner;

	@FindBy(xpath = ("//div[@class='card-content']"))
	public List<WebElement> courses;

	@FindBy(xpath = "//ul[@class='ais-InfiniteHits-list']/li[1]//h2")
	public static WebElement FirstCourse;

	@FindBy(xpath = "//ul[@class='ais-InfiniteHits-list']/li[1]//h2")
	public static WebElement CourseName1;

	@FindBy(xpath = "//ul[@class='ais-InfiniteHits-list']/li[1]//span[@class='ratings-text']")
	public static WebElement Rating1;

	@FindBy(xpath = "//div[@class='ProductGlance']/div[5]//span")
	public static WebElement Duration1;

	@FindBy(xpath = "//ul[@class='ais-InfiniteHits-list']/li[2]//h2")
	public static WebElement SecondCourse;

	@FindBy(xpath = "//ul[@class='ais-InfiniteHits-list']/li[2]//h2")
	public static WebElement CourseName2;

	@FindBy(xpath = "//ul[@class='ais-InfiniteHits-list']/li[2]//span[@class='ratings-text']")
	public static WebElement Rating2;

	@FindBy(xpath = "//div[@class='ProductGlance']/div[5]//span")
	public static WebElement Duration2;

	String screenshot = "Searchcourseshot";
	String parentWindow;

	public SearchCourse(WebDriver l_driver, ExtentTest logger) {

		super(l_driver, logger);
		PageFactory.initElements(driver, this);
	}

	public void LanguageSelect() {

		// Language field is clicked
		logger.log(Status.INFO, "Clicking the language field");
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(Language)).click();
		reportPass("The Language Field is selected");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Language "English" is selected
		logger.log(Status.INFO, "Selecting the Language \"English\" ");
		English.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		reportPass("The Language Field with value \"English\" is selected");

	}

	public void LevelSelect() {

		// Level field is clicked
		logger.log(Status.INFO, "Clicking the level field");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Level.click();
		reportPass("Successfully Clicked the level field");
		
		// Level "Beginner" is selected
		logger.log(Status.INFO, "Selecting the Level \"Beginner\"");
		Beginner.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		reportPass("The Level Field with value \"Beginner\" is selected");

	}

	public void FirstCourse() {

		

		// Extract the name of the first course
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("First course name = " + CourseName1.getText());
		reportPass("First course name = " + CourseName1.getText());

		// Extract the rating of the first course
		System.out.println("First course rating = " + Rating1.getText());
		reportPass("First course rating = " + Rating1.getText());
		parentWindow = driver.getWindowHandle();

		logger.log(Status.INFO, "Clicking the first course");
		FirstCourse.click();

		reportPass("First course is clicked and multiple handle is implemented");
		Set<String> handles1 = driver.getWindowHandles();

		for (String childWindow1 : handles1) {
			if (!childWindow1.contentEquals(parentWindow)) {

				// Navigate to child window
				driver.switchTo().window(childWindow1);
				takeScreenShot(screenshot);

				// Extract the duration of the first course
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("First course duration = " + Duration1.getText());
				reportPass("First course duration = " + Duration1.getText());
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.close();

			}

			// Get back to Parent window
			driver.switchTo().window(parentWindow);
		}

	}

	public void SecondCourse() {

		// Second course is clicked
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger.log(Status.INFO, "Clicking the second course");
	    SecondCourse.click();

		// Extract the name of the second course
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Second course name = " + CourseName2.getText());
		reportPass("Second course name = " + CourseName2.getText());

		// Extract the rating of the second course
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Second course rating = " + Rating2.getText());
		reportPass("Second course rating = " + Rating2.getText());

		reportPass("Second course is clicked and multiple handle is implemented");
		Set<String> handles2 = driver.getWindowHandles();

		for (String childWindow2 : handles2) {
			if (!childWindow2.contentEquals(parentWindow)) {

				// Navigate to child window
				driver.switchTo().window(childWindow2);
				takeScreenShot(screenshot);

				// Extract the duration of the second course
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("Second course duration = " + Duration2.getText());
				reportPass("Second course duration = " + Duration2.getText());

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.close();
			}
		}

		// Get back to Parent window
		driver.switchTo().window(parentWindow);

	}

}
