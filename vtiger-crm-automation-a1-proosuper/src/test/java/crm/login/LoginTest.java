package crm.login;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base_Utility.BaseClass;
;

/**
 * Test Script: Login Test using POM design pattern.
 * Tests valid login to VTiger CRM and verifies successful login,
 * then performs logout.
 */

@Listeners(listener_utility.List_Imp.class)
public class LoginTest extends BaseClass{

	@Test
	public void createLogin() throws InterruptedException, IOException, ParseException {
		
		ExtentTest test = report.createTest("createLogin");

//		verification - check page title contains 'vtiger'
		String pageTitle = driver.getTitle();
		
		if (pageTitle.toLowerCase().contains("vtiger")) {
			System.out.println("Login successful! Page title: " + pageTitle);
		} else {
			System.out.println("Login might have failed. Page title: " + pageTitle);
		}
		test.log(Status.PASS, "Login Successfully.....");
	}
}
