package test_Cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.ConfigReader;
import base_Class.Base_Class;
import pageObjects.Dashboard.Page_Object_Dashboard;

public class TestApplyLeave extends Base_Class {
	@Test(priority = 0)
	public void applyLeaveWithMandatoryFields() throws InterruptedException {
	    // **Arrange**: Prepare for the test by navigating to the Apply Leave page
		dashboardPage.clickOnLeaveTab();
		leaveListPage.clickOnApplyTab();
		
	    // Assert that Apply Leave page is loaded
		System.out.println(applyLeavePage.applyleavePageLoaded());
		Assert.assertEquals(applyLeavePage.applyleavePageLoaded(), "Apply Leave", "Apply Leave page did not load!");
		
	    // **Act**: Perform the action of applying leave
		applyLeave(ConfigReader.getConfigPropertyData("startDateCancelled"),ConfigReader.getConfigPropertyData("monthName"),
				ConfigReader.getConfigPropertyData("endDateCancelled"));

	    // **Assert**: Verify that leave application was successful
	Assert.assertTrue(leaveListPage.leaveListPageWithRecords(),"Leave records were not found after applying leave!");
	
	}

}
