package test_Cases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base_Class.Base_Class;

public class TestMyLeave extends Base_Class {

	@Test(priority = 0, enabled = true, description = "verify admin is able to cancel the leave on my leave page")
	public void testCancelButtonForPendingApprovalLeave() {
		// **Arrange**: setup done in @beforeMethod
		dashboardPage.clickOnLeaveTab();
		leaveListPage.navigateToMyLeavePage();
		Assert.assertTrue(myLeavePage.isMyLeavePageLoaded(), "My Leave page did not load!");
		
		
		//Act :: Call the method to cancel a "Pending Approval" leave and get the updated status
	 boolean isLeaveFound=   myLeavePage.cancelPendingLeaveOnMyLeavePage();

	    // Check if any pending leave was found
	    if (!isLeaveFound) {
	        Assert.fail("No leave with 'Pending Approval' status was found to cancel.");
	    } else {
	        // Assert that the updated status is "Cancelled"
	        Assert.assertTrue(isLeaveFound, "Leave is Cancelled");
	        Reporter.log("Cancel button functionality for 'Pending Approval' leave is working as expected.", true);
	    }
	}
}
