package pageObjects.Leave;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


import Utilities.BrowserUtilities;
import pageObjects.Profile.Dynamic_Profile;

public class EntitlementsPage extends BrowserUtilities{
	
	public WebDriver driver;
	public EntitlementsPage(WebDriver driver) {
		//super(driver);
		this.driver = driver;
		
	}
	

	@FindBy(css = "input[placeholder='Type for hints...']")
	private WebElement clickOnEmpField;
	
//	@FindBy(xpath="(//div[contains(@class,'oxd-autocomplete-text-input')])[1]")
	//public WebElement clickOnEmpField;

	
	@FindBy(xpath = "//div[@class='oxd-autocomplete-option']/span")
	List<WebElement> EmploysList;

	@FindBy(xpath = ("(//div[contains(@class,'oxd-select-text--active')])[2]"))
	WebElement clickSelectLeaveType;
	
	@FindBy(xpath = "//div[@class='oxd-select-option']/span")
	List<WebElement> getListOfAllStatus;

    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text--active')])[2]")
    private WebElement leavePeriodField;
    
    @FindBy(xpath="//div[@class='oxd-select-option']/span")
    List<WebElement> listOfLeavePeriod;

    @FindBy(xpath ="//div[contains(@class,'oxd-input-group')]/div[2]/input")
    private WebElement entitlementField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;
    
    @FindBy(xpath="//div[@class='oxd-form-actions']/button[1]")
    private WebElement cancelButton;
    
  
    @FindBy(css="h5[class*='oxd-table-filter-title']")
    WebElement getTitle;
   
    @FindBy(xpath="//div[contains(@class,'orangehrm-modal-footer')]/button[2]")
    WebElement confirmButton;
    
	@FindBy(xpath = "(//p[contains(@class,'oxd-toast-content-text')])[2]")
	WebElement getSucessMessage;

    public void enterEmployeeName(String name) {
    	String empArr[] = name.split(" ");
    	int max=3;
    	int count=0;
    	while(count<max) {
    		try {
    			 count++;
    		
    	// Define Fluent Wait
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))        // Maximum wait time
            .pollingEvery(Duration.ofSeconds(5))        // Polling interval
            .ignoring(NoSuchElementException .class);    // Exceptions to ignore

        // Define a condition for the wait
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
            	if(clickOnEmpField.isEnabled()) {
            		return clickOnEmpField;
            	}
            	else return null;
                
            }
        });
        
        
        if(element!=null) {
        	System.out.print("click on emply name is found" +" "+element);

        }
    		
    		}
    		catch(Exception e) {
    			if(max==count) {
    				 System.out.println("Maximum retries reached. Exiting.");
    			}
    			}
    		}
       

    	
    	
    	
    	
    	//WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
    	//wait.until(ExpectedConditions.stalenessOf(clickOnEmpField));
    //	waitElementToVisible(driver,20,clickOnEmpField);

		clickOnEmpField.click();
		System.out.println("empyname" + empArr[0]);
		clickOnEmpField.sendKeys(empArr[0]);
		waitForElementsVisible(driver, 3, EmploysList);
		System.out.println("size of emply" + EmploysList.size());

		for (WebElement list : EmploysList) {
			if (list.getText().contains(empArr[0])) {
				list.click();
				break;
			}
		}}
    

    public void selectLeaveType(String type) {
    	clickSelectLeaveType.click();
		waitForElementsVisible(driver, 2, getListOfAllStatus);
		for (WebElement status : getListOfAllStatus) {
			if (status.getText().equals(type)) {
				status.click();
				break;
			}
		}

    }

    public void enterLeavePeriod(String period) {
        leavePeriodField.click();
        
        waitForElementsVisible(driver,2,listOfLeavePeriod);
        for(WebElement leave:listOfLeavePeriod) {
        	if(leave.getText().contains(period)) {
        		leave.click();
        		break;
        	}
        }
    }

    public void enterEntitlement(String entitlement) {
        entitlementField.sendKeys(entitlement);
    }

    public void clickSave() {
        saveButton.click();
    }
    
    public void clickConfirm() {
    	confirmButton.click();
    }
    
    public String getSucessMessage() {
		waitElementToVisible(driver, 3, getSucessMessage);
		return getSucessMessage.getText();
	}
    
    public void getCancelButton() {
    	cancelButton.click();    }

	public void addEntitlements(List<WebElement> entitlementsOption)  {
		waitForElementsVisible(driver,2,entitlementsOption);
		for(WebElement option:entitlementsOption) {
			if(option.getText().equals("Add Entitlements")) {
				option.click();
			
				break;
			}
		}
		
	}
	
	

	public void employeEntitlements(List<WebElement> entitlementsOption) {
		for(WebElement option:entitlementsOption) {
			if(option.getText().equals("Employee Entitlements")) {
				option.click();
				break;
			}
		}
		
	}
	
	public String getTitleEmployeEntitlements() {
		waitElementToVisible(driver,2,getTitle);
		return getTitle.getText();
	}
	
	public void myEntitlements(List<WebElement> entitlementsOption) {
		for(WebElement option:entitlementsOption) {
			if(option.getText().equals("My Entitlements")) {
				option.click();
				break;
			}
		}
		
	}
	
	
	
	
}
