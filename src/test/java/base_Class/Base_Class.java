package base_Class;

import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Drivers.DriverFactory;
import Utilities.ConfigReader;
import pageObjects.Dashboard.Page_Object_Dashboard;
import pageObjects.Directory.Page_Object_Directory;
import pageObjects.Leave.ApplyLeavePage;
import pageObjects.Leave.EntitlementsPage;
import pageObjects.Leave.LeaveListPage;
import pageObjects.Leave.MyLeavePage;
import pageObjects.Login.Login_Page_Objects;
import pageObjects.Profile.Dynamic_Profile;
import pageObjects.Recuritment.RecuirmentsPage;
import pageObjects.Search.SearchPage;
import test_Cases.SearchPageTest;

//public class Base_Class

public class Base_Class {

	// public WebDriver driver;
	public LeaveListPage leaveListPage;
	public Login_Page_Objects loginPage;
	public ApplyLeavePage applyLeavePage;
	public Page_Object_Dashboard dashboardPage;
	public Dynamic_Profile profileName;
	public MyLeavePage myLeavePage;
	public EntitlementsPage entitlementsPage;
	public Page_Object_Directory directoryObj;
	public SearchPage searchObj;
	public RecuirmentsPage recPageObj;
	public WebDriver driver;
//	private String browser;

	@Parameters("browserName")
	@BeforeMethod
	public void setup(@Optional String browserFromTestNg) throws IOException, URISyntaxException {
		 // Priority Order: TestNG Parameter > System Property > Config File
		
	//	String browser = (System.getProperty("browser") != null) ? System.getProperty("browser")  : ConfigReader.getConfigPropertyData("browser");
		
		String selectedBrowser = (browserFromTestNg != null) ? browserFromTestNg : System.getProperty("browser", ConfigReader.getConfigPropertyData("browser"));
        
		System.out.println(selectedBrowser + " browser name is "); 
		driver = DriverFactory.getDriverInstance(selectedBrowser).getDriver();
		driver.manage().window().maximize();
		// driver.manage().deleteAllCookies();
		System.out.println("Thread: " + Thread.currentThread().getName() + ", Driver Instance: " + driver);
		loginPage = new Login_Page_Objects(driver);
		leaveListPage = new LeaveListPage(driver);

		applyLeavePage = new ApplyLeavePage(driver);
		profileName = new Dynamic_Profile(driver);
		myLeavePage = new MyLeavePage(driver);
		entitlementsPage = new EntitlementsPage(driver);
		directoryObj = new Page_Object_Directory(driver);
		searchObj = new SearchPage(driver);
		recPageObj = new RecuirmentsPage(driver);

		// **Arrange**: Prepare for each test
		loginPage.loadLoginPage();
		loginPage.Default_Username(ConfigReader.getConfigPropertyData("username"));
		loginPage.Default_Password(ConfigReader.getConfigPropertyData("password"));
		dashboardPage = loginPage.Click_Login();
		Assert.assertEquals(dashboardPage.dashboardLoaded(), "Dashboard", "Dashboard did not load sucessfully");

	}

	// resuable method for apply leave
	public void applyLeave(String startDate, String endDate, String monthName) throws InterruptedException {

		applyLeavePage.selectLeaveType();
		applyLeavePage.selectFromDate(startDate, monthName);
		applyLeavePage.selectToDate(endDate, monthName);
		applyLeavePage.clickApplyLeave();
	}

	@AfterMethod()
	public void quit() {
		DriverFactory.quit();
	}

}
