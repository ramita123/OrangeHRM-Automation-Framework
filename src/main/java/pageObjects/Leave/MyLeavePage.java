package pageObjects.Leave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Utilities.BrowserUtilities;

public class MyLeavePage extends BrowserUtilities {

	public WebDriver driver;

	public MyLeavePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	//	super(driver);
		this.driver = driver;
		// driver argument means that this driver will be used to lok yp elements and
		// second
		// argument is passed to tell pagefactory that this class weblements need to be
		// intiated

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h5[class*='oxd-table-filter-title']")
	WebElement getTitle;

	// Locator for the leave rows in the leave list table
	@FindBy(xpath = "//div[@class='oxd-table-card']//div[contains(@class,'oxd-table-row--with-border')]")
	private List<WebElement> leaveRows;

	public boolean isMyLeavePageLoaded() {
		waitElementToVisible(driver, 2, getTitle);
		return getTitle.getText().contains("My Leave");
	}

	// Method to process all "Pending" leaves: Check status, click More Options, and
	// cancel
	public boolean cancelPendingLeaveOnMyLeavePage() {
		boolean isLeaveFoundWithPendingStatus = false;
		scrollPage(0, 400, driver);
		waitForElementsVisible(driver, 2, leaveRows);
		for (WebElement row : leaveRows) {
			String status = getLeaveStatus(row);
			if (status.contains("Pending Approval")) {
				isLeaveFoundWithPendingStatus = true;

				// Cancel the leave if the Cancel button is displayed
				cancelLeave(row);
				System.out.println("leave is cancelled");
				break;

			}
		}
		return isLeaveFoundWithPendingStatus;
	}

	private void cancelLeave(WebElement row) {
		row.findElement(By.xpath(".//div[@class='oxd-table-cell-actions']/button")).click();

	}

	// method to get the leave status
	private String getLeaveStatus(WebElement row) {
		waitElementToVisible(driver, 3, row.findElement(By.xpath(".//div[7]")));
		return row.findElement(By.xpath(".//div[7]/div")).getText();
	}

}
