package crm.organization;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base_Utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;

import object_repository.HomePage;

import object_repository.OrganizationPage;

/**
 * Test Script: Create Organization Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Organizations module
 *   3. Click Create Organization
 *   4. Fill in the organization form (name, phone, email, website)
 *   5. Save and verify
 *   6. Logout
 */

@Listeners(listener_utility.List_Imp.class)
public class CreateOrgTest extends BaseClass{

	@Test
	public void createOrg() throws EncryptedDocumentException, IOException {
		
		ExtentTest test = report.createTest("createOrg");

//		get the data from Excel file
		FileUtility fUtil = new FileUtility();
		String orgName = fUtil.getDataFromExcelFile("org", 2, 0) + JavaUtility.generateRandomNumber();

//		========== POM: HomePage ==========
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

//		========== POM: OrganizationPage ==========
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationButton().click();

//		fill the Create Organization form
		op.getOrganizationName().sendKeys(orgName);
		op.getPhone().sendKeys("9876543210");
		op.getEmail().sendKeys("testorg@vtiger.com");
		op.getWebsite().sendKeys("www.testorg.com");

//		save the record
		op.getSaveButton().click();

//		verification
		String actOrgName = op.getDetailViewOrganizationName().getText();
		Assert.assertEquals(actOrgName, orgName);
		test.log(Status.PASS, "Created Organization Successfully......");

	}
}
