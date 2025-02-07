package pageObjects.Recuritment;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

import Utilities.BrowserUtilities;

public class AddCandidatePage extends BrowserUtilities {

	private WebDriver driver;

	public AddCandidatePage(WebDriver driver) {
		// super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		// driver argument means that this driver will be used to lok yp elements and
		// second
		// argument is passed to tell pagefactory that this class weblements need to be
		// intiated

		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "firstName")
	WebElement firstName;

	@FindBy(name = "lastName")
	WebElement lastName;

	@FindBy(xpath = "(//input[@placeholder='Type here'])[1]")
	WebElement emailField;

	/*
	 * @FindBy(xpath="//label[text()='Email']") WebElement email;
	 */

	@FindBy(css = "button[type='submit']")
	WebElement submitBtn;

	@FindBy(xpath = "//h6[text()='Add Candidate']")
	WebElement addCandidatePage;

	@FindBy(xpath = "(//p[contains(@class,'oxd-toast-content-text')])[2]")
	WebElement getSucessMessage;

	@FindBy(xpath = "//div[@class='oxd-file-input-div']")
	WebElement uploadFile;

	@FindBy(xpath = "//div[@class='oxd-file-input-div']")
	WebElement uploadedFileName;

	public String addCandidatePageLoaded() {
		waitElementToVisible(driver, 2, addCandidatePage);
		return addCandidatePage.getText();
	}

	public void addnewCandidate(String Name, String LastName, String Email) throws IOException {
		try {
			if (Name != null) {
				firstName.sendKeys(Name);
			}
		} catch (Exception e) {
			System.out.println("first name is mandatry"+" "+e.getStackTrace());
		}
		lastName.sendKeys(LastName);
		// selenium 4 features

		waitElementToVisible(driver, 10, emailField);
		emailField.sendKeys(Email);

		// scrollPage(0, 600, driver);
		/*
		 * *WebElement consent
		 * =driver.findElement(By.xpath("//label[text()='Consent to keep data']")); //
		 * driver.findElement(RelativeLocator.with(By.tagName("i")).toLeftOf(consent)).
		 * click(); getSingleElementScreenhsot(consent,Name); getHeightWidth(consent);
		 */
	}

	public void clickSubmit() {
		// scrollPage(0,600,driver);
		submitBtn.click();
	}

	public String getSucessMessage() {
		waitElementToVisible(driver, 10, getSucessMessage);
		return getSucessMessage.getText();
	}

	public void clickResumeUploadBtn(String autoItExePath) throws InterruptedException {
		// scrollPage(0,600,driver);
		waitElementToBeClickable(driver, 10, uploadFile);
		uploadFile.click();
		uploadFileHelper(autoItExePath);
		 Thread.sleep(3000);

		// uploadedFileName.getText();
		// String text= getErrorMessage();
		// System.out.println(text);

	}

	private boolean isErrorMessageDisplayed() {
		boolean isDisplayed = false;
		try {
			WebElement errorMessage = driver
					.findElement(By.xpath("(//span[contains(@class,'oxd-input-field-error-message')])[4]"));
			waitElementToVisible(driver, 10, errorMessage);

			if (errorMessage.isDisplayed()) {
				isDisplayed = true;
			}
		} catch (NoSuchElementException e) {
			System.out.println("coming to catch");

		}

		if (isDisplayed == true) {
			return true;
		} else {
			return false;
		}
	}

	private String getErrorMessage() {
		WebElement errorMessage = driver
				.findElement(By.xpath("(//span[contains(@class,'oxd-input-field-error-message')])[4]"));
		waitElementToVisible(driver, 10, errorMessage);
		return errorMessage.getText();
	}

}
