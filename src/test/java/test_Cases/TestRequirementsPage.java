package test_Cases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.BrowserUtilities;
import Utilities.ConfigReader;
import base_Class.Base_Class;
import pageObjects.Recuritment.AddCandidatePage;
import utils.Retry;

public class TestRequirementsPage extends Base_Class {

	public static String sheetName = "TestcasesData";

	@Test(priority = 1, dataProvider = "getHRMTestData", retryAnalyzer=Retry.class ,description = "verify that new candidate is getting added")
	public void validateAddNewCandiadte(String Name, String LastName, String Email,String ResumeFilePath) throws IOException, InterruptedException {
		System.out.println("name is"+Name);
		System.out.println("last name is"+LastName);
		System.out.println("emal is"+Email);
		System.out.println("filepath is"+" "+ResumeFilePath);
		// **Arrange
		dashboardPage.clickOnRequirementsTab();
		recPageObj.clickAddButton();
		AddCandidatePage candidateObj = new AddCandidatePage(driver);
		Assert.assertEquals(candidateObj.addCandidatePageLoaded(), "Add Candidate");

		// **Act
		candidateObj.addnewCandidate(Name, LastName, Email);
		candidateObj.clickResumeUploadBtn(ResumeFilePath);
		//candidateObj.clickSubmit();

		// **Assert
		Assert.assertEquals(candidateObj.getSucessMessage(), "Successfully Saved", "sucessfull message did not come");

	}

	
	
	
	@DataProvider
	public Object[][] getHRMTestData() {
		BrowserUtilities obj = new BrowserUtilities();
		return obj.getTestData(sheetName);
	}
}
