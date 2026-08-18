package crm.product;

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
import object_repository.HomePage;
import object_repository.ProductPage;

/**
 * Test Script: Create Product Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Products module
 *   3. Click Create Product
 *   4. Fill in the product form (name, code, unit price, qty per unit)
 *   5. Save and verify
 *   6. Logout
 */

@Listeners(listener_utility.List_Imp.class)
public class CreateProductTest extends BaseClass{

	@Test
	public void createProduct() throws InterruptedException, IOException, ParseException {
		
		ExtentTest test = report.createTest("createProduct");

//		generate a unique product name
		String productName = "AutoProduct" + JavaUtility.generateRandomNumber();
		String productCode = "PROD" + JavaUtility.generateRandomNumber();

//		========== POM: HomePage ==========
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();

//		========== POM: ProductPage ==========
		ProductPage pp = new ProductPage(driver);
		pp.getCreateProductButton().click();

//		fill the Create Product form
		pp.getProductName().sendKeys(productName);
		pp.getProductCode().sendKeys(productCode);
		pp.getQtyPerUnit().sendKeys("1");
		pp.getUnitPrice().sendKeys("999.99");
		pp.getCommissionRate().sendKeys("10");
		pp.getQtyInStock().sendKeys("100");
		pp.getReorderLevel().sendKeys("10");

//		select product category
		Select categorySelect = new Select(pp.getProductCategory());
		categorySelect.selectByIndex(1); // select first available category

//		save the record
		pp.getSaveButton().click();

//		verification
		String actProductName = pp.getDetailViewProductName().getText();
		
		Assert.assertEquals(actProductName, productName);
		test.log(Status.PASS, "Product Created Successfully....");

	}
}
