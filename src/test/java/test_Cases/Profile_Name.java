package test_Cases;

import org.testng.annotations.Test;

import base_Class.Base_Class;
import pageObjects.Login.Login_Page_Objects;
import pageObjects.Profile.Dynamic_Profile;

public class Profile_Name extends Base_Class


{
  @Test
  public void getProfileName() {
	  String UserName = profileName.usrName();
	  System.out.println("The user name is: " + UserName);
  }

}
