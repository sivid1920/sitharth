package com.base.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class OpenBrowser {
	public static WebDriver driver;

	public WebDriver openBrowser(String browsername) {

		try {

			if (browsername.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");
				// to disable notification pop-up
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--disable-notifications");
				driver = new ChromeDriver(option);
				System.out.println("Opening " + browsername + " browser.................");
				driver.manage().window().maximize(); // to enter maximized screen

			}

			else if (browsername.equalsIgnoreCase("IE")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\IEDriverServer.exe");
				// to disable notification pop-up
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.elementScrollTo(ElementScrollBehavior.BOTTOM);
				driver = new InternetExplorerDriver(options);
				driver.manage().window().maximize(); // to enter maximized screen

			}

			else {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\geckodriver.exe");
				// to disable notification pop-up
				FirefoxOptions options = new FirefoxOptions();
				options.setProfile(new FirefoxProfile());
				options.addPreference("dom.webnotifications.enabled", false);
				driver = new FirefoxDriver(options);
				System.out.println("Opening " + browsername + " browser.................");
				driver.manage().window().maximize(); // to enter maximized screen

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return driver;

	}
}
