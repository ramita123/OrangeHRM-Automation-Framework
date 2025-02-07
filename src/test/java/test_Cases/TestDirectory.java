package test_Cases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base_Class.Base_Class;

public class TestDirectory extends Base_Class {

	@Test(priority = 0, enabled = true, description = "when searching with valid name , the employ name is coming in list")
	public void testSearchEmployeWithValidName() {
		System.out.println(Thread.currentThread().getName());
		// Arrange
		dashboardPage.clickDirectoryTab();

		// Act
		directoryObj.searchWithEmployeeName(profileName.usrName());
		directoryObj.clickSearchButton();

		// Assert
		Assert.assertTrue(directoryObj.validateSearchedEmployeeName(profileName.usrName()),
				"searched name is not matching");
		
		System.out.println("first finished");
	}

	@Test(priority = 1, enabled=true,description = "Verify contact details are displayed correctly")
	public void testContactDetails() {
		System.out.println(Thread.currentThread().getName());
		// Arrange
		dashboardPage.clickDirectoryTab();

		// Act
		directoryObj.searchWithEmployeeName(profileName.usrName());
		directoryObj.clickSearchButton();
		directoryObj.clickProfileCard();

		// Assert
		Assert.assertEquals(directoryObj.getContactNumber(), "112-898-7612");
		Assert.assertEquals(directoryObj.getContactEmail(), "paul1@osohrm.com");
		System.out.println("2nd finished");
	}

	@Test(priority = 2, enabled=false,description = "Verify QR Code is visible")
	public void testQRCodeVisibility() {
		System.out.println(Thread.currentThread().getName());
		// Arrange
		dashboardPage.clickDirectoryTab();

		// Act
		directoryObj.searchWithEmployeeName(profileName.usrName());
		directoryObj.clickSearchButton();
		directoryObj.clickProfileCard();

		// Assert
		Assert.assertTrue(directoryObj.getQRCodeDisplayed(), "QR code is not displayed");
		System.out.println("3rd finished");
	}

	@Test(priority = 3, enabled=false,description = "Verify phone and email icons are functional")
	public void testContactIcons() {
		System.out.println(Thread.currentThread().getName());
		// Arrange
		dashboardPage.clickDirectoryTab();

		// Act
		directoryObj.searchWithEmployeeName(profileName.usrName());
		directoryObj.clickSearchButton();

		directoryObj.clickProfileCard();
		// Asssert
		Assert.assertTrue(directoryObj.getPhoneIcon(), "the phone icon is not displayed");
		Assert.assertTrue(directoryObj.getEmailIcon(), "the email icons is not displayed");
		System.out.println("fourth completed");
	}

}
