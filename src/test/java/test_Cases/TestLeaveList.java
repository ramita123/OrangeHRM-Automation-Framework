package test_Cases;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Utilities.ConfigReader;
import base_Class.Base_Class;
import pageObjects.Dashboard.Page_Object_Dashboard;
import pageObjects.Leave.LeaveListPage;

public class TestLeaveList extends Base_Class {

	static String comment = "please approve my leave";

	@Test(priority = 0, enabled = true, description = "verify that leave list page loads successfully with no records")
	public void verifyLeaveListPageShowsNoRecords() throws InterruptedException {
		// **Arrange**: setup done in @beforeMethod
		dashboardPage.clickOnLeaveTab();

		// **Act**: Perform action to load Leave List page
		String leavePageTitle = leaveListPage.leavePageLoaded();
		String noRecordsMessage = leaveListPage.leaveListPageNoRecordMessage().trim();

		// **Assert**: Validate that no records are found
		Assert.assertEquals(leavePageTitle, "Leave", "Leave page did not load sucessfully");
		Assert.assertEquals(noRecordsMessage, "No Records Found", "No Records Found message not displayed!");

	}

	@Test(priority = 1, enabled = true, description = "verify that leave list page loads successfully with records")
	public void verifyLeaveListPageShowsRecords() throws InterruptedException {

		// **Arrange**: Set up by applying a leave
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");
		leaveListPage.clickOnApplyTab();
		Assert.assertEquals(applyLeavePage.applyleavePageLoaded(), "Apply Leave", "Apply Leave page did not load!");

		// apply leave
		applyLeave(ConfigReader.getConfigPropertyData("startDateCancelled"),
				ConfigReader.getConfigPropertyData("endDateCancelled"),
				ConfigReader.getConfigPropertyData("monthName"));

		// **Act**: Navigate back to Leave List and fetch records
		dashboardPage.clickOnLeaveTab();

		// **Assert**: Verify that leave records are present
		Assert.assertTrue(leaveListPage.leaveListPageWithRecords(), "No one has applied the leave ");

	}

	@Test(priority = 2, enabled = true, description = "verify date range filter with no records")
	public void verifyFilteringLeaveWithNoRecords() {
		// **Arrange**: setup done in @beforeMethod
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Failed to load Leave page");

		// **Act**: Apply the date range filter
		leaveListPage.applyDateFilter(ConfigReader.getConfigPropertyData("startDate"),
				ConfigReader.getConfigPropertyData("endDate"), ConfigReader.getConfigPropertyData("monthName"));
		leaveListPage.clickSearchButton();

		// **Assert**: Verify no records are found for the given date range
		Assert.assertEquals(leaveListPage.leaveListPageNoRecordMessage().trim(), "No Records Found",
				"Expected 'No Records Found' message not displayed for empty date range.");
	}

	@Test(priority = 3, enabled = true, description = "verify date range filter with records")
	public void verifyFilteringLeaveWithRecords() throws InterruptedException {

		// **Arrange**: Set up by applying a leave
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");
		leaveListPage.clickOnApplyTab();
		Assert.assertEquals(applyLeavePage.applyleavePageLoaded(), "Apply Leave", "Apply Leave page did not load!");

		// apply leave
		applyLeave(ConfigReader.getConfigPropertyData("startDateCancelled"),
				ConfigReader.getConfigPropertyData("endDateCancelled"),
				ConfigReader.getConfigPropertyData("monthName"));

		dashboardPage.clickOnLeaveTab();
		Assert.assertTrue(leaveListPage.leaveListPageWithRecords(), "No one has applied the leave ");

		// **Act**: Apply the date range filter
		leaveListPage.applyDateFilter(ConfigReader.getConfigPropertyData("startDateCancelled"),
				ConfigReader.getConfigPropertyData("endDateCancelled"),
				ConfigReader.getConfigPropertyData("monthName"));
		leaveListPage.clickSearchButton();

		// **Assert**: Verify that leave records are present
		Assert.assertTrue(leaveListPage.leaveListPageWithRecords(), "No one has applied the leave ");
	}

	@Test(priority = 4, enabled = false, description = "verify reset  filter with records")
	public void verifyResetDateFilter() throws InterruptedException {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		leaveListPage.clickOnApplyTab();
		// apply leave
		applyLeave(ConfigReader.getConfigPropertyData("startDateCancelled"),
				ConfigReader.getConfigPropertyData("endDateCancelled"),
				ConfigReader.getConfigPropertyData("monthName"));
		leaveListPage.clickSearchButton();
		dashboardPage.clickOnLeaveTab();
		leaveListPage.applyDateFilter(ConfigReader.getConfigPropertyData("startDate"),
				ConfigReader.getConfigPropertyData("endDate"), ConfigReader.getConfigPropertyData("monthName"));
		leaveListPage.clickSearchButton();

		// check records with in the applied date range
		// List<WebElement> filteredRecords = leaveListPage.getLeaveRecords();

		// **Act**: Click on Reset button
		leaveListPage.clickResetFilterButton();

		// **Assert**:verify reset btn working fine

	}

	@Test(priority = 5, enabled = true, description = "Verify filtering leaves with Pending status (default) and no records")
	public void verifyFilteringLeavesWithPendingStatusAndNoRecords() {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// **Assert**: Verify Pending Approval is selected by default
		String defaultStatus = leaveListPage.getSelectedLeaveStatus();
		Assert.assertEquals(defaultStatus, "Pending Approval", "Default status is not 'Pending Approval'!");

		// **Act**: Apply the date range filter
		leaveListPage.applyDateFilter(ConfigReader.getConfigPropertyData("startDate"),
				ConfigReader.getConfigPropertyData("endDate"),
				ConfigReader.getConfigPropertyData("monthNameLeaveList"));
		leaveListPage.clickSearchButton();

		// **Assert**: Verify "No Records Found" message is displayed
		Assert.assertEquals(leaveListPage.leaveListPageNoRecordMessage().trim(), "No Records Found",
				"Expected 'No Records Found' message not displayed for empty date range.");

		// **Act**: Reset the filter
		leaveListPage.clickResetFilterButton();

		// **Assert**: Verify default filter values after reset
		Assert.assertEquals(leaveListPage.getSelectedLeaveStatus(), "Pending Approval",
				"Status did not reset correctly!");
	}

	@Test(priority = 6, enabled = true, description = "Verify filtering leaves with Pending status (default) and with records")
	public void verifyFilteringLeavesWithPendingStatusAndWithRecords() throws InterruptedException {

		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		leaveListPage.clickOnApplyTab();
		Assert.assertEquals(applyLeavePage.applyleavePageLoaded(), "Apply Leave", "Apply Leave page did not load!");

		// apply leave
		applyLeave(ConfigReader.getConfigPropertyData("startDateCancelled"),
				ConfigReader.getConfigPropertyData("endDateCancelled"),
				ConfigReader.getConfigPropertyData("monthName"));
		dashboardPage.clickOnLeaveTab();
		Assert.assertTrue(leaveListPage.leaveListPageWithRecords(), "No one has applied the leave ");

		// **Assert**: Verify Pending Approval is selected by default
		String defaultStatus = leaveListPage.getSelectedLeaveStatus();
		Assert.assertEquals(defaultStatus, "Pending Approval", "Default status is not 'Pending Approval'!");

		// **Act**: Apply the date range filter
		leaveListPage.applyDateFilter(ConfigReader.getConfigPropertyData("startDate"),
				ConfigReader.getConfigPropertyData("endDate"),
				ConfigReader.getConfigPropertyData("monthNameLeaveList"));
		leaveListPage.clickSearchButton();

		// **Assert**: all the leaves are coming with status pending after applying the
		// filter
		Assert.assertTrue(leaveListPage.verifyAllRecordsWithLeaveStatus("Pending Approval"),
				"Not all records have 'Pending' status.");

	}

	// Rest filter is pending

	@Test(priority = 7, enabled = true, description = "Verify filtering leaves with cancelled status and with records")
	public void verifyFilteringLeavesWithCancelledStatusAndWithRecords() throws InterruptedException {

		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// **Act**: choose cancelledLeave Status
		leaveListPage.applyDateFilter(ConfigReader.getConfigPropertyData("startDateCancelled"),
				ConfigReader.getConfigPropertyData("endDateCancelled"),
				ConfigReader.getConfigPropertyData("monthName"));
		leaveListPage.clearDefaultLeaveStatusOption();
		leaveListPage.chooseLeaveStatusFromList("Cancelled");
		leaveListPage.clickSearchButton();

		// **Assert**: all the leaves are coming with status cancelled after applying
		// the filter

		if (leaveListPage.getLeaveRecords().isEmpty()) {
			Assert.fail("No records found with the selected filter criteria.");
		} else {
			Assert.assertTrue(leaveListPage.verifyAllRecordsWithLeaveStatus("Cancelled"),
					"Not all records have 'Cancelled' status.");

		}
	}

	@Test(priority = 8, enabled = true, description = "Verify filtering leaves with leave type")
	public void verifyFilteringLeavesWithLeaveType() throws InterruptedException {

		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// **Act**: choose leave type
		leaveListPage.applyDateFilter(ConfigReader.getConfigPropertyData("startDateCancelled"),
				ConfigReader.getConfigPropertyData("endDateCancelled"),
				ConfigReader.getConfigPropertyData("monthName"));
		leaveListPage.selectLeaveType("CAN - FMLA");
		leaveListPage.clickSearchButton();

		// **Assert**: all the leaves are coming with the selected leave type after
		// applying the filter
		if (leaveListPage.getLeaveRecords().isEmpty()) {
			Assert.fail("No records found with the selected filter criteria.");
		} else {
			Assert.assertTrue(leaveListPage.verifyAllRecordsWithLeaveType("CAN - FMLA"),
					"Not all records have the expected leave type: CAN - FMLA.");
		}

	}

	@Test(groups = {
			"smoke" }, priority = 9, enabled = true, description = "Verify filtering leaves with employe Name")
	public void verifyFilteringLeavesWithEmployeName() throws InterruptedException {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// **Act**: choose leave type
		leaveListPage.applyDateFilter(ConfigReader.getConfigPropertyData("startDateCancelled"),
				ConfigReader.getConfigPropertyData("endDateCancelled"),
				ConfigReader.getConfigPropertyData("monthName"));
		leaveListPage.searchWithEmployeeName(profileName.usrName());
		leaveListPage.clickSearchButton();

		// **Assert**: all the leaves are coming with the selected leave type after
		// applying the filter
		if (leaveListPage.getLeaveRecords().isEmpty()) {
			Assert.fail("No records found with the selected filter criteria.");
		} else {
			Assert.assertTrue(leaveListPage.verifyAllRecordsWithEmployName(profileName.usrName()),
					"Not all records have the expected leave type: " + profileName.usrName());
		}

	}

	@Test(priority = 10, enabled = true, description = "Verify add comment")
	public void verifyAddComment() throws InterruptedException {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// apply leave
		if (leaveListPage.getLeaveRecords().size() == 0) {
			leaveListPage.clickOnApplyTab();
			Assert.assertEquals(applyLeavePage.applyleavePageLoaded(), "Apply Leave", "Apply Leave page did not load!");

			// **Act**: add Comment
			applyLeave(ConfigReader.getConfigPropertyData("startDateComment"),
					ConfigReader.getConfigPropertyData("endDateComment"),
					ConfigReader.getConfigPropertyData("monthNameComment"));
			dashboardPage.clickOnLeaveTab();
		}

		// **Act**: add Comment
		leaveListPage.clickMoreOptionBtn();
		leaveListPage.clickAddCommentBtn();
		leaveListPage.addCommnet(comment);
		leaveListPage.submitComment();

		// **Assert**: verify that comment id added sucessfully
		Assert.assertEquals(leaveListPage.getSucessMessage(), "Successfully Saved", "sucessfull message did not come");
	}

	@Test(groups = { "smoke" }, priority = 11, enabled = true, description = "Verify leave request details page")
	public void verifyleaveRequestDetails() throws InterruptedException {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// apply leave
		if (leaveListPage.getLeaveRecords().size() == 0) {
			leaveListPage.clickOnApplyTab();
			Assert.assertEquals(applyLeavePage.applyleavePageLoaded(), "Apply Leave", "Apply Leave page did not load!");

			// **Act**: add Comment
			applyLeave(ConfigReader.getConfigPropertyData("startDateComment"),
					ConfigReader.getConfigPropertyData("endDateComment"),
					ConfigReader.getConfigPropertyData("monthNameComment"));
			dashboardPage.clickOnLeaveTab();
		}

		// **Act**: add Comment
		leaveListPage.clickMoreOptionBtn();
		leaveListPage.clickViewDetailsOption();

		// **Assert**: verify that leave request loads successfully & EMploye,leavedate,
		// levae tyep are present
		Assert.assertEquals(leaveListPage.verifyLeaveRequestDetailsLoads(), "Leave Request Details",
				"Leave request details did not load");
		System.out.println(leaveListPage.getEmployeName());
		System.out.println(leaveListPage.getLeaveDate());
		System.out.println(leaveListPage.getLeaveType());
	}

	@Test(groups = {
			"smoke" }, priority = 12, enabled = false, description = "Verify  add comment functionality on leave request details page")
	public void verifyAddCommentOnleaveRequestDetails() throws InterruptedException {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// **Act**: add Comment
		if (leaveListPage.getLeaveRecords().size() != 0) {
			leaveListPage.clickMoreOptionBtn();
			leaveListPage.clickViewDetailsOption();
			Assert.assertEquals(leaveListPage.verifyLeaveRequestDetailsLoads(), "Leave Request Details",
					"Leave request details did not load");

		} else {
			Assert.assertTrue(leaveListPage.getLeaveRecords().isEmpty(), "no record found");
		}

		leaveListPage.clickMoreOptionBtn();
		leaveListPage.clickAddCommentBtn();
		;
		leaveListPage.addCommnet(comment);
		leaveListPage.submitComment();

		// **Assert**: verify that comment id added sucessfully
		Assert.assertEquals(leaveListPage.getComment(), comment, "comment is not added");
		Assert.assertEquals(leaveListPage.getSucessMessage(), "Successfully Saved", "sucessfull message did not come");
	}

	//this one need to debug
	@Test(groups = { "smoke" }, priority = 13, enabled = false, description = "Verify leave request details page")
	public void verifyCommentsBtnOnleaveRequestDetails() throws InterruptedException {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// **Act**: add Comment
		if (leaveListPage.getLeaveRecords().size() != 0) {
			leaveListPage.clickMoreOptionBtn();
			leaveListPage.clickViewDetailsOption();
			Assert.assertEquals(leaveListPage.verifyLeaveRequestDetailsLoads(), "Leave Request Details",
					"Leave request details did not load");

		} else {
			Assert.assertTrue(leaveListPage.getLeaveRecords().isEmpty(), "no record found");
		}

		leaveListPage.getCommentsBtn();
		leaveListPage.addCommnet(comment);
		leaveListPage.submitComment();

		// **Assert**: verify that comment id added sucessfully
		Assert.assertEquals(leaveListPage.getComment(), comment, "comment is not added");
		Assert.assertEquals(leaveListPage.getSucessMessage(), "Successfully Saved", "sucessfull message did not come");

	}

	@Test(groups = { "smoke" }, priority = 14, enabled = true, description = "Verify cancel leave request details page")
	public void verifyCancelLeaveOnleaveRequestDetails() throws InterruptedException {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// apply leave
		if (leaveListPage.getLeaveRecords().size() == 0) {
			leaveListPage.clickOnApplyTab();
			Assert.assertEquals(applyLeavePage.applyleavePageLoaded(), "Apply Leave", "Apply Leave page did not load!");

			applyLeave(ConfigReader.getConfigPropertyData("startDateCancelled"),
					ConfigReader.getConfigPropertyData("endDateCancelled"),
					ConfigReader.getConfigPropertyData("monthNameCancelled"));
			dashboardPage.clickOnLeaveTab();
		}

		leaveListPage.clickMoreOptionBtn();
		leaveListPage.clickViewDetailsOption();
		Assert.assertEquals(leaveListPage.verifyLeaveRequestDetailsLoads(), "Leave Request Details",
				"Leave request details did not load");
		// leaveListPage.clickMoreOptionBtn();
		leaveListPage.cancelPendingLeaves();

		// **Assert**: verify that leave is cancelled successfully
		Assert.assertEquals(leaveListPage.getLeaveStatusOnRequestDetails(), "Cancelled");

		// redirect to leave list page
		leaveListPage.getBackBtn();

		// **Assert**: verify that user back to Leave List page
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");
	}

	@Test(groups = {"smoke" }, priority = 15, enabled = true, description = "Verify user is on PIm page ")
	public void testPIMInfoPage() throws InterruptedException {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// apply leave
		if (leaveListPage.getLeaveRecords().size() == 0) {
			leaveListPage.clickOnApplyTab();
			Assert.assertEquals(applyLeavePage.applyleavePageLoaded(), "Apply Leave", "Apply Leave page did not load!");

			applyLeave(ConfigReader.getConfigPropertyData("startDateCancelled"),
					ConfigReader.getConfigPropertyData("endDateCancelled"),
					ConfigReader.getConfigPropertyData("monthNameCancelled"));
			dashboardPage.clickOnLeaveTab();
		}
		
		leaveListPage.clickMoreOptionBtn();
		leaveListPage.clickViewPIMInfo();
		Assert.assertEquals(leaveListPage.verifyPIMPageLoaded(),"PIM","PIM Page did not loaded");
	System.out.println("Employe Name on PIM"+" "+ 	leaveListPage.getPIMEmployeeName());

	}
	
	
	@Test(groups = { "smoke" }, priority = 16, enabled = true, description = "Verify cancel leave request details page")
	public void testCancelLeaveOnleaveListPage() throws InterruptedException {
		// **Arrange**: Navigate to Leave List Page and enter a specific date range
		dashboardPage.clickOnLeaveTab();
		Assert.assertEquals(leaveListPage.leavePageLoaded(), "Leave", "Leave page did not load successfully!");

		// apply leave
		if (leaveListPage.getLeaveRecords().size() == 0) {
			leaveListPage.clickOnApplyTab();
			Assert.assertEquals(applyLeavePage.applyleavePageLoaded(), "Apply Leave", "Apply Leave page did not load!");

			applyLeave(ConfigReader.getConfigPropertyData("startDateCancelled"),
					ConfigReader.getConfigPropertyData("endDateCancelled"),
					ConfigReader.getConfigPropertyData("monthNameCancelled"));
			dashboardPage.clickOnLeaveTab();
		}
		//Act:: click on cancel button to cancel the leave
		leaveListPage.clickMoreOptionBtn();
		leaveListPage.clickCancelLeaveOptionOnListPage();
		
		
		//Assert:: verify that leave is cancelled ;
				Assert.assertEquals(leaveListPage.getSucessMessage(), "Successfully Updated", "sucessfull message did not come");
			}

	}


