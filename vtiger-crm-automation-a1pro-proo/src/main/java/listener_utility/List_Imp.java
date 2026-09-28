package listener_utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base_Utility.BaseClass;

public class List_Imp implements ISuiteListener, ITestListener {
	
	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("It Will Execute Before The @BeforeSuite");
		
		long time = System.currentTimeMillis();
		
		ExtentSparkReporter spark = new ExtentSparkReporter("ad_reports/"+time+".html");
		spark.config().setDocumentTitle("vtiger");
		spark.config().setReportName("Login Report");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		
		report.setSystemInfo("ATE", "Arunendra");
		report.setSystemInfo("Browser", "edge");
		report.setSystemInfo("OS Name", "Microsoft Windows 10 Pro");
		report.setSystemInfo("OS Manufacturer", "Microsoft Corporation");
		report.setSystemInfo("Time Zone", "Indian Standard Time");
		report.setSystemInfo("System Name", "DESKTOP-69RE7KL");

	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName+ "is passed....");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		test.log(Status.FAIL, methodName+ "is Fail");
		
		//Taking Screenshot
		TakesScreenshot tks = (TakesScreenshot)BaseClass.sdriver;
		String ss = tks.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(ss);
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+ "is Skipped");
	}
	
	@Override
	public void onFinish(ISuite suite) {
		
		System.out.println("It Will Execute After The @AfterSuite");
		
		//Report Backup
		report.flush();
	}

}
