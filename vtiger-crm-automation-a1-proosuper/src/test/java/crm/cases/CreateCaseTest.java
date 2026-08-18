package crm.cases;

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
import object_repository.CasePage;


/**
 * Test Script: Create Case (Trouble Ticket) Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Cases (Trouble Tickets / HelpDesk) module
 *   3. Click Create Ticket
 *   4. Fill in the case form (title, status, priority, severity, description)
 *   5. Save and verify
 *   6. Logout
 */

@Listeners(listener_utility.List_Imp.class)
public class CreateCaseTest extends BaseClass{

	@Test
	public void createCase() throws InterruptedException, IOException, ParseException {
		
		ExtentTest test = report.createTest("createCase");

//		generate a unique ticket title
		String ticketTitle = "AutoTicket" + JavaUtility.generateRandomNumber();

//		Navigate to HelpDesk/Cases module directly via URL
//		(since 'Cases' might be under 'More' menu)
		String url = "http://localhost:8888/";
		driver.get(url + "index.php?module=HelpDesk&action=index");

//		========== POM: CasePage ==========
		CasePage cp = new CasePage(driver);
		cp.getCreateCaseButton().click();

//		fill the Create Ticket form
		cp.getTicketTitle().sendKeys(ticketTitle);

//		select ticket status
		Select statusSelect = new Select(cp.getTicketStatus());
		statusSelect.selectByVisibleText("Open");

//		select ticket priority
		Select prioritySelect = new Select(cp.getTicketPriority());
		prioritySelect.selectByVisibleText("High");

//		select ticket severity
		Select severitySelect = new Select(cp.getTicketSeverity());
		severitySelect.selectByVisibleText("Major");

//		add description
		cp.getDescription().sendKeys("This is an automated test ticket created by Selenium POM script. "
				+ "Testing the create ticket functionality of VTiger CRM.");

//		save the record
		cp.getSaveButton().click();

//		verification
		String actTitle = cp.getDetailViewTitle().getText();
		Assert.assertEquals(actTitle, ticketTitle);
		test.log(Status.PASS, "Case Created Successfully.....");

	}
}
