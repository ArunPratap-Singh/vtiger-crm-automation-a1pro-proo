package advance_reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SauceDemoTest1 {
	
	ExtentReports report;
	
	@BeforeSuite
	public void repConfig() {
		
		//Report Configuration.means project level
		ExtentSparkReporter spark = new ExtentSparkReporter("ad_reports/rep1.html");
		spark.config().setDocumentTitle("Sauce Demo Login");
		spark.config().setReportName("Login Report");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("ATE", "Arunendra");
		report.setSystemInfo("Browser", "edge");
		report.setSystemInfo("window", "11");
}
	
	@Test
	public void login() throws InterruptedException {
		
		ExtentTest test = report.createTest("login");
		
		WebDriver driver = new EdgeDriver();
		
		driver.get("https://www.saucedemo.com/");
		
		Thread.sleep(3000);
		driver.quit();
		
		//test.log(Status.PASS, "This is Passed.....");
		//test.log(Status.FAIL, "This is failed....");
		test.log(Status.SKIP, "This is Skipped.....");
		//test.log(Status.INFO, "This is just Information.....");
		
	}
	
	@Test
	public void logout() throws InterruptedException {
		
		ExtentTest test = report.createTest("logout");
		
		WebDriver driver = new EdgeDriver();
		
		Thread.sleep(3000);
		driver.quit();
		
		test.log(Status.PASS, "This is Passed....");
//		test.log(Status.FAIL, "This is Failed....");
//		test.log(Status.SKIP, "This is Skipped....");
		test.log(Status.INFO, "This is just Information....");
		
	}
	
	@AfterSuite
	public void repBackUp() {
	
		//Report BackUp
		report.flush();
	}

}
