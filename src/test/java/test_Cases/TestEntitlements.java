package test_Cases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base_Class.Base_Class;

public class TestEntitlements extends Base_Class {

	@Test(priority = 0, enabled = true, description = "verify admin is able to add the entitlements")
	public void testAddEntitlements()  {
		// **Arrange**: setup done in @beforeMethod
		dashboardPage.clickOnLeaveTab();
		List<WebElement> entitlementsOption = leaveListPage.navigateToEntitlementsPage();
		
		//Act:: add the entitlements
		entitlementsPage.addEntitlements(entitlementsOption);
		String employName = profileName.usrName();
		Reporter.log(employName, true);
		entitlementsPage.enterEmployeeName(employName);
		entitlementsPage.selectLeaveType("CAN - Bereavement");
		entitlementsPage.enterLeavePeriod("2023");
		entitlementsPage.enterEntitlement("15");
		entitlementsPage.clickSave();
		entitlementsPage.clickConfirm();
		
		//Assert:: verify that entitlement is added successfully
		Assert.assertEquals(entitlementsPage.getSucessMessage(), "Successfully Saved",
				"entitlements is not added sucessfully");
	}

	
	///need to add seassion expiry check in login page
	
	
	@Test(priority = 1, enabled = false, description = "verify admin is able to click on cancel button and redirects to leave entitlements")
	public void testCancelButton()  {
		// **Arrange**: setup done in @beforeMethod
		dashboardPage.clickOnLeaveTab();
		List<WebElement> entitlementsOption = leaveListPage.navigateToEntitlementsPage();
		entitlementsPage.addEntitlements(entitlementsOption);
		
		//Act :: click on cancel button
		entitlementsPage.getCancelButton();
		
		//Assert:: Verify that user is redirected to leave entitlements page
		Assert.assertEquals(entitlementsPage.getTitleEmployeEntitlements(), "Leave Entitlements");
	}

}
