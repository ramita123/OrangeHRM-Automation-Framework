package test_Cases;

import org.testng.annotations.Test;

import base_Class.Base_Class;
import pageObjects.Dashboard.employee_Distribution_Graph;
import pageObjects.Login.Login_Page_Objects;
import side_Panel_Objects.Side_Panel_Objects;

public class Graph_test extends Base_Class

{
  @Test
  public void f() 
  
  {
	//  Login_Page_Objects Login = new Login_Page_Objects(driver);
	  loginPage.directLogin("Admin"," Admin123");
	  //Side_Panel_Objects clkDashboard = new Side_Panel_Objects(driver);
//	  clkDashboard.clkDashboard();
	 // employee_Distribution_Graph Check_DataPoints = new employee_Distribution_Graph(driver);
	//  Check_DataPoints.tstGraph();
	  
  }
}
