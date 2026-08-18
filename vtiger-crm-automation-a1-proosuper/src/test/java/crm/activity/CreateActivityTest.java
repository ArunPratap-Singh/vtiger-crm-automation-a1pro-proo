package crm.activity;

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
import object_repository.ActivityPage;


/**
 * Test Script: Create Activity (Event/Meeting) Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Activities (Calendar) module
 *   3. Click Add Event
 *   4. Fill in the event form (subject, type, status, start/end date, location)
 *   5. Save and verify
 *   6. Logout
 */

@Listeners(listener_utility.List_Imp.class)
public class CreateActivityTest extends BaseClass{

	@Test
	public void createActivity() throws InterruptedException, IOException, ParseException {
		ExtentTest test = report.createTest("createActivity");

//		generate a unique activity subject
		String activitySubject = "AutoMeeting" + JavaUtility.generateRandomNumber();

//		navigate to Calendar / Add Event via URL
		String url = "http://localhost:8888/";
		driver.get(url + "index.php?module=Calendar&action=EditView&activity_mode=Events");

//		========== POM: ActivityPage ==========
		ActivityPage ap = new ActivityPage(driver);

//		fill the Create Event form
		ap.getSubject().sendKeys(activitySubject);

//		select activity type
		Select typeSelect = new Select(ap.getActivityType());
		typeSelect.selectByVisibleText("Meeting");

//		select event status
		Select statusSelect = new Select(ap.getEventStatus());
		statusSelect.selectByVisibleText("Planned");

//		set start date and time
		ap.getStartDate().clear();
		ap.getStartDate().sendKeys("2026-08-20");

//		set end date
		ap.getEndDate().clear();
		//ap.getEndDate().sendKeys("2026-07-26");

//		save the record
		ap.getSaveButton().click();
		Thread.sleep(2000);
		
		//verification - check subject in detail view
		String actSubject = ap.getDetailViewSubject().getText();
		
		Assert.assertEquals(actSubject, activitySubject);
		test.log(Status.PASS, "Activity Created Successfully......");
	}
}
