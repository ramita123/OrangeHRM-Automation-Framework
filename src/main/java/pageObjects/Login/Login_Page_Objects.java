package pageObjects.Login;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.ConfigReader;
import pageObjects.Dashboard.Page_Object_Dashboard;

public class Login_Page_Objects

{

	public WebDriver driver;
	public WebDriverWait wait;

	//CREATING CONSTRUCTOR TO INITIALIZE THE WEBDRIVER

	public Login_Page_Objects(WebDriver driver)
	{//System.out.print("instance of driver on login page"+ driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	// ASSIGNING LOCATORS FOR EVERY LOGIN PAGE ELEMENT


	private By Default_User_Name_Field = By.xpath("//input[@placeholder='Username']");                                        // USERNAME
	private By Default_Password = By.xpath("//input[@placeholder='Password']");                                               // PASSWORD
	private By Login_Button = By.xpath("//button[@type='submit']");
	private By Forget_Password = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']");        //FORGET PASSOWORD
//<<<<<<< HEAD

//=======
	
	
	public void loadLoginPage() {
		
		driver.get(ConfigReader.getConfigPropertyData("url"));
	}
//>>>>>>> refs/remotes/origin/master
	//RE-USABLE METHOD FOR USERNAME

	public void Default_Username(String username)
	{
		@SuppressWarnings("unused")
		WebElement Wait_Username= ((FluentWait<WebDriver>) wait).until(ExpectedConditions.visibilityOfElementLocated(Default_User_Name_Field));
		Wait_Username.click();
		driver.findElement(Default_User_Name_Field).clear();
		driver.findElement(Default_User_Name_Field).sendKeys(username);


	}


	// RE-USABLE METHOD FOR PASSOWRD

	public void Default_Password(String password)
	{
		WebElement entrPassword = wait.until(ExpectedConditions.elementToBeClickable(Default_Password));
		entrPassword.click();
		entrPassword.clear();
		entrPassword.sendKeys("admin123");

	}

	//RE-USABLE METHOD TO CLICK ON LOGIN BUTTON

	
	public Page_Object_Dashboard Click_Login()

	{
		driver.findElement(Login_Button).click();
		Page_Object_Dashboard dashboardPage= new Page_Object_Dashboard(driver);
		return dashboardPage;
	}

	//RE-USABLE METHOD TO CLICK ON THE FORGET PASSOWRD LINK

	public void Click_Forget_Password()
	{
		driver.findElement(Forget_Password).click();
	}

	public void directLogin(String username , String password)
	{
		Default_Username(username);
		Default_Password(password);
		Click_Login();
		
	}
}
