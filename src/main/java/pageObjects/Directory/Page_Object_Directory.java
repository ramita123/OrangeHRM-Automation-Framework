package pageObjects.Directory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BrowserUtilities;

public class Page_Object_Directory extends BrowserUtilities {

	public Page_Object_Directory(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//super(driver);
		this.driver = driver;
		// driver argument means that this driver will be used to lok yp elements and
		// second
		// argument is passed to tell pagefactory that this class weblements need to be
		// intiated

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h5[class*='oxd-table-filter-title']")
	WebElement title;

	@FindBy(xpath = "//div[@class='oxd-autocomplete-wrapper']/div/input")
	WebElement clickOnEmpField;

	@FindBy(xpath = "//div[@class='oxd-autocomplete-option']/span")
	List<WebElement> EmploysList;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchBtn;

	@FindBy(css = "p[class*='orangehrm-directory-card-header']")
	WebElement profileName;

	@FindBy(css = "div[class*='orangehrm-directory-card']")
	WebElement profileCard;

	@FindBy(css = "//div[@class='orangehrm-corporate-directory-sidebar'] //p[contains(@class,'orangehrm-directory-card-subtitle')]")
	WebElement jobTitle;

	@FindBy(css = "i[class*='bi-telephone-fill']")
	WebElement phoneIcon;

	@FindBy(css = "i[class*='bi-envelope-fill']")
	WebElement emailIcon;

	@FindBy(css = "img[class='orangehrm-qr-code']")
	WebElement qrCode;
	
	@FindBy(xpath="(//p[contains(@class,'oxd-text--toast-title')])[1]")
	WebElement contactNumber;
	
	@FindBy(xpath="(//p[contains(@class,'oxd-text--toast-title')])[2]")
	WebElement contactEmail;
	
	

	public String getTitle() {
		return title.getText();
	}

	public void searchWithEmployeeName(String emplyName) {
		String empArr[] = emplyName.split(" ");
		clickOnEmpField.click();
//		System.out.println("empyname" + empArr[0]);
		clickOnEmpField.sendKeys(empArr[0]);
		waitForElementsVisible(driver, 3, EmploysList);
	//	System.out.println("size of emply" + EmploysList.size());

		for (WebElement list : EmploysList) {
			if (list.getText().contains(empArr[0])) {
				list.click();
				break;
			}
		}
	}

	public void clickSearchButton() {
		//waitElementToBeClickable(driver,2,searchBtn);
		searchBtn.click();
	}

	public boolean validateSearchedEmployeeName(String expectedName) {
		// Locate the element displaying the employee name in the result
		waitElementToVisible(driver,2, profileName);
		String actualName = profileName.getText();
		String actualNameArr[] = actualName.split(" ");
		System.out.println("actaul name I am getting" + actualNameArr[0]);
		  if(!expectedName.contains(actualNameArr[0])) {
			  return false;
		  }
		return true;
	}

	public void clickProfileCard() {
		waitElementToVisible(driver,3,profileCard);
		profileCard.click();
	}
	public String getContactNumber() {
		waitElementToVisible(driver,3,contactNumber);
		return contactNumber.getText();
	}
	
	public String getContactEmail() {
		waitElementToVisible(driver,3,contactEmail);
		return contactEmail.getText();
	}
	
	public String getDepartment() {
		profileCard.click();
		return jobTitle.getText();
	}

	public boolean getQRCodeDisplayed() {
		scrollPage(0, 400, driver);
		waitElementToVisible(driver,2,qrCode);
		return qrCode.isDisplayed();
	}

	public boolean getPhoneIcon() {
		waitElementToVisible(driver,3,phoneIcon);
		return phoneIcon.isDisplayed();
	}

	public boolean getEmailIcon() {
		waitElementToVisible(driver,3,emailIcon);
		return emailIcon.isDisplayed();
	}

}
