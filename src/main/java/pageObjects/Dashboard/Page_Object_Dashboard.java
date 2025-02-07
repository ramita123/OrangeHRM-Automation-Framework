package pageObjects.Dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BrowserUtilities;

public class Page_Object_Dashboard extends BrowserUtilities{
	

		public WebDriver driver;

		public Page_Object_Dashboard(WebDriver driver) {
		//	super(driver);
			this.driver = driver;
			// driver argument means that this driver will be used to lok yp elements and
			// second
			// argument is passed to tell pagefactory that this class weblements need to be
			// intiated
			PageFactory.initElements(driver, this);
		}

		@FindBy(xpath="//h6[contains(@class,'breadcrumb-module')]")
		WebElement dashboard;
		
		@FindBy(xpath="//ul[@class='oxd-main-menu']/li[3]/a")
		WebElement leaveBtn;
		
		@FindBy(xpath="//ul[@class='oxd-main-menu']/li[5]/a")
		WebElement RequirementsBtn;
		
		@FindBy(linkText="Directory")
		WebElement directory;
		
		public String dashboardLoaded() {
			waitElementToVisible(driver,3,dashboard);
			return	dashboard.getText();
		}
		
		public void clickOnLeaveTab() {
			staticWait(2);
			leaveBtn.click();
		}
		
		public void clickDirectoryTab() {
			
			directory.click();
		}
		
		
		public void clickOnRequirementsTab() {
			waitElementToBeClickable(driver,10,RequirementsBtn);
			RequirementsBtn.click();		}

	}



