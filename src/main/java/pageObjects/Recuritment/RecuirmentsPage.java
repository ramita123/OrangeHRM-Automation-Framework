package pageObjects.Recuritment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BrowserUtilities;

public class RecuirmentsPage extends BrowserUtilities{
	
	private WebDriver driver;
	public RecuirmentsPage(WebDriver driver) {
	//	super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		// driver argument means that this driver will be used to lok yp elements and
		// second
		// argument is passed to tell pagefactory that this class weblements need to be
		// intiated

		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="div[class='orangehrm-header-container'] button")
	WebElement addBtn;
	
	@FindBy(xpath = "//a[text()='Vacancies']")
	WebElement vacanciesTab;
	
	public void clickAddButton()
	{
		waitElementToVisible(driver,2,addBtn);
		addBtn.click();
		
	}
	
	 public void goToVacanciesTab() {
	        vacanciesTab.click();
	    }
	
	
	
	
	
	
}
