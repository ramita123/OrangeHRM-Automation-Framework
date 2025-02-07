package pageObjects.Leave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.BrowserUtilities;

public class ApplyLeavePage extends BrowserUtilities{
	public WebDriver driver;
   public  WebDriverWait wait;


	public ApplyLeavePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	//	super(driver);
		this.driver = driver;
		// driver argument means that this driver will be used to lok yp elements and
		// second
		// argument is passed to tell pagefactory that this class weblements need to be
		// intiated

		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="h6[class*='orangehrm-main-title']")
	WebElement applyLeaveTitle;
	
	@FindBy(css="div[class*='oxd-select-text--active']")
	WebElement clickLeaveTypeField;
	
	@FindBy(xpath="//div[@class='oxd-select-option']/span")
	WebElement leaveTypeDropdown;
	
	@FindBy(xpath="//div[@class='oxd-select-text-input']")
	WebElement getSelectedLeaveType;
	
	
	
	@FindBy(xpath="(//div[@class='oxd-date-input']/input)[1]")
	WebElement clickOnStartDateField;
	
	
	
	@FindBy(xpath="(//div[@class='oxd-date-input']/input)[2]")
	WebElement clickOnEndDateField;
	
	@FindBy(xpath="//div[@class='oxd-calendar-date']")
	List<WebElement> listOfAllDays;
	
	@FindBy(xpath="//div[@class='oxd-calendar-selector-month-selected']/p")
	WebElement getSelectedMonth;
	
	@FindBy(xpath="//button[@class='oxd-icon-button'] //i[contains(@class,'bi-chevron-right')]")
	WebElement chooseNextMonth;
	
	@FindBy(xpath="//button[@class='oxd-icon-button'] //i[contains(@class,'bi-chevron-left')]")
	WebElement choosePreviousMonth;
	
    @FindBy(xpath = "//button[@type='submit']")
	WebElement submitBtn;
	
	public String applyleavePageLoaded() {
		waitElementToVisible(driver, 2, applyLeaveTitle);
		return applyLeaveTitle.getText();
	}
	
	public String selectLeaveType() {
		staticWait(1);
		clickLeaveTypeField.click();
		leaveTypeDropdown.click();
	return 	getSelectedLeaveType.getText();
	
	}
	

	
	
    public void selectFromDate(String day,String month) {
    	selectDateFromCalendar(month,driver,clickOnStartDateField, listOfAllDays, day,getSelectedMonth,chooseNextMonth);
      
    }

    public void selectToDate(String day,String month)  {
    	selectDateFromCalendar(month,driver,clickOnEndDateField, listOfAllDays, day,getSelectedMonth,chooseNextMonth);
      
    }
    
	public void clickApplyLeave()  {
		submitBtn.click();
	}
}
