package crm.vendor;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base_Utility.BaseClass;
import generic_utility.JavaUtility;
import object_repository.VendorPage;

/**
 * Test Script: Create Vendor Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Vendors module
 *   3. Click Create Vendor
 *   4. Fill in the vendor form (name, phone, email, website, address)
 *   5. Save and verify
 *   6. Logout
 */

@Listeners(listener_utility.List_Imp.class)
public class CreateVendorTest extends BaseClass{

	@Test
	public void createVendor() throws InterruptedException, IOException, ParseException {
		
		ExtentTest test = report.createTest("createVendor");

//		generate a unique vendor name
		String vendorName = "AutoVendor" + JavaUtility.generateRandomNumber();

//		Navigate to Vendors module via URL (may be under More menu)
		String url = "http://localhost:8888/";
		driver.get(url + "index.php?module=Vendors&action=index");

//		========== POM: VendorPage ==========
		VendorPage vp = new VendorPage(driver);
		vp.getCreateVendorButton().click();

//		fill the Create Vendor form
		vp.getVendorName().sendKeys(vendorName);
		vp.getPhone().sendKeys("040-88776655");
		vp.getEmail().sendKeys("autovendor@vendortest.com");
		vp.getWebsite().sendKeys("www.autovendor.com");
//		vp.getFax().sendKeys("040-88776600");
//		vp.getGlAccount().sendKeys("GL-001");

//		fill address
		vp.getStreet().sendKeys("456 Vendor Street");
		vp.getCity().sendKeys("Hyderabad");
		vp.getState().sendKeys("Telangana");
		vp.getPostalCode().sendKeys("500001");
		vp.getCountry().sendKeys("India");

//		add description
		vp.getDescription().sendKeys("Automated test vendor created by Selenium POM script.");

//		save the record
		vp.getSaveButton().click();

//		verification
		String actVendorName = vp.getDetailViewVendorName().getText();
		
		Assert.assertEquals(actVendorName, vendorName);
		test.log(Status.PASS, "Vendor Created Successfully.....");

	}
}
