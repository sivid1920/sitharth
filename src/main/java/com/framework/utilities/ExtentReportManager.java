package com.framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

/*Note - this method is used for creating report for each test runs*/
public class ExtentReportManager {
	public static ExtentReports report;

	// Method for Extent Report
	public static ExtentReports getReportInstance(String browsername, String Testreportname) {

		if (report == null) {
			String reportName = Testreportname + " " + DateUtil.getDate() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "\\TestReports\\" + reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Build Number", "10.0.99.12");
			report.setSystemInfo("Browser", browsername);

			htmlReporter.config().setDocumentTitle("UAT UI Automation Results");
			htmlReporter.config().setReportName("All Headlines UI Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}
		return report;
	}

}
