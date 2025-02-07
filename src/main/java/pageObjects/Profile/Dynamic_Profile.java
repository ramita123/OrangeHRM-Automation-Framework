package pageObjects.Profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base_Objects.Base_Objects;

public class Dynamic_Profile extends Base_Objects

{
	public Dynamic_Profile(WebDriver driver)
	{
		super(driver);
	}

	private By UserName = By.xpath("//p[@class='oxd-userdropdown-name']");
	
	public String usrName()
	{
		WebElement ClickProfile = wait.until(ExpectedConditions.elementToBeClickable(UserName));
	//	ClickProfile.click();
		String captureUSername = ClickProfile.getText();
		System.out.println("Captured Text Is:"+captureUSername);
		return captureUSername;
	}
	
}
