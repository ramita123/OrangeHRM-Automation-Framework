package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	public static   ExtentReports getExtentReportObject() {
		
		ExtentSparkReporter reporter= new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//index.html");
		reporter.config().setDocumentTitle("Web Automation Of HRM Project");
		reporter.config().setReportName("Ramita Sambyal");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setOfflineMode(true);
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "RamitaSambyal");
		return extent;
	}

}
