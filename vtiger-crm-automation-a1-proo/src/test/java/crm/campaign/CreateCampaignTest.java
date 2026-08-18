package crm.campaign;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base_Utility.BaseClass;
import generic_utility.JavaUtility;
import object_repository.CampaignPage;

/**
 * Test Script: Create Campaign Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Campaigns module
 *   3. Click Create Campaign
 *   4. Fill in the campaign form (name, type, status, budget, dates)
 *   5. Save and verify
 *   6. Logout
 */

@Listeners(listener_utility.List_Imp.class)
public class CreateCampaignTest extends BaseClass{

	@Test
	
	public void createCampaign() throws InterruptedException, IOException, ParseException {
		
		ExtentTest test = report.createTest("createCampaign");

//		generate a unique campaign name
		String campaignName = "AutoCampaign" + JavaUtility.generateRandomNumber();

		String url = "http://localhost:8888/";
//		Navigate to Campaigns module via URL (may be under More menu)
		driver.get(url + "index.php?module=Campaigns&action=index");

//		========== POM: CampaignPage ==========
		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampaignButton().click();

//		fill the Create Campaign form
		cp.getCampaignName().sendKeys(campaignName);

//		select campaign type
		Select typeSelect = new Select(cp.getCampaignType());
		typeSelect.selectByVisibleText("Email");

//		select campaign status
		Select statusSelect = new Select(cp.getCampaignStatus());
		statusSelect.selectByVisibleText("Active");

		cp.getClosingDate().sendKeys("12/31/2026");

//		fill budget and cost fields
		cp.getBudgetCost().sendKeys("10000");
		cp.getActualCost().sendKeys("7500");
		cp.getExpectedRevenue().sendKeys("50000");
		cp.getExpectedResponseCount().sendKeys("200");

//		add description
		cp.getDescription().sendKeys("Automated test campaign created by Selenium POM script. "
				+ "This is an email marketing campaign for testing.");

//		save the record
		cp.getSaveButton().click();

//		verification
		String actCampaignName = cp.getDetailViewCampaignName().getText();
		
		Assert.assertEquals(actCampaignName, campaignName);
		test.log(Status.PASS, "Created Campaign Successfully.....");

	}
}
