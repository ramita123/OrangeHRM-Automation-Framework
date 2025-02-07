package test_Cases;

import org.testng.annotations.Test;

import base_Class.Base_Class;
import pageObjects.Login.Login_Page_Objects;

public class TC_Login_001 extends Base_Class //IMPORTING BASE CLASS TO LAUNCH THE DRIVER 

{
  @Test
  public void Run_TC_Login_001() 
  
  {
	  //INITIALIZING LOGIN PAGE CLASS
	 
//	  Login_Page_Objects Login_Page = new Login_Page_Objects(driver);
	  
	  //CALLING RE-USABLE METHODS FROM LOGIN_PAGE 
	  
	  loginPage.Default_Username("Admin");
	  loginPage.Default_Password("admin123");
	loginPage.Click_Login();
	  
	  
	  
  }
}
