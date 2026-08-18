package crm.debug;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base_Utility.BaseClass;


/**
 * Debug script to print all input field IDs/names on Organization create page.
 */

@Listeners(listener_utility.List_Imp.class)
public class InspectOrganizationForm extends BaseClass{
	
	@Test
    public void inspectOrg() throws InterruptedException, IOException, ParseException {
		
		ExtentTest test = report.createTest("inspectOrg");
        
        driver.findElement(By.linkText("Organizations")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("img[title='Create Organization...']")).click();
        Thread.sleep(2000);

        System.out.println("===== Organization Form Fields =====");
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        for (WebElement el : inputs) {
            String id = el.getAttribute("id");
            String name = el.getAttribute("name");
            String type = el.getAttribute("type");
            if (!"hidden".equals(type)) {
                System.out.println("INPUT: id='" + id + "' name='" + name + "' type='" + type + "'");
            }
        }

        List<WebElement> selects = driver.findElements(By.tagName("select"));
        for (WebElement el : selects) {
            String id = el.getAttribute("id");
            String name = el.getAttribute("name");
            System.out.println("SELECT: id='" + id + "' name='" + name + "'");
        }

        List<WebElement> textareas = driver.findElements(By.tagName("textarea"));
        for (WebElement el : textareas) {
            String id = el.getAttribute("id");
            String name = el.getAttribute("name");
            System.out.println("TEXTAREA: id='" + id + "' name='" + name + "'");
        }
        test.log(Status.PASS, "Inspected Organization Form Successfully....");

    }
}
