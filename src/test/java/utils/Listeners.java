package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.BrowserUtilities;
import Utilities.ExtentReportManager;

public class Listeners implements ITestListener {

	ExtentReports extent = ExtentReportManager.getExtentReportObject();
	ExtentTest extentTestObj;

	public void onTestStart(ITestResult result) {
		// not implemented
		extentTestObj = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// not implemented
		extentTestObj.log(Status.PASS, "Test is passed" + result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		// not implemented
		WebDriver driver = null;
		extentTestObj.log(Status.FAIL, result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = BrowserUtilities.getScreenshot(driver, result.getMethod().getMethodName());

		extentTestObj.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		// not implemented
	}

	public void onFinish(ITestContext context) {
		// not implemented
		extent.flush();
	}

}
