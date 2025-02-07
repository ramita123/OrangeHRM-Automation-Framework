package pageObjects.Search;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BrowserUtilities;

public class SearchPage extends BrowserUtilities{

	public SearchPage(WebDriver driver) {
	//	super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		// driver argument means that this driver will be used to lok yp elements and
		// second
		// argument is passed to tell pagefactory that this class weblements need to be
		// intiated

		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class*='menu-search'] input")
	WebElement search;
	
	@FindBy(xpath="//ul[@class='oxd-main-menu']/li/a/span")
	List<WebElement> matchingElements;
	
	public List<String> validateSearch(String searchText) {
		search.sendKeys(searchText);
		return get_matching_elements(searchText);
	}
	
	
	private List<String> get_matching_elements(String text) {
	List<String > list= new ArrayList<>();
		for(WebElement element:matchingElements) {
			System.out.println(element.getText());
			if(element.getText().toLowerCase().contains(text)) {
				list.add (element.getText());
			}
		}
		
	return list;
	}
}
