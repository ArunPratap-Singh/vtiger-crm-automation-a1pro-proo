package typesOfExecution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ContactTest {
	
	WebDriver driver;
	
	@Parameters("bro")
	@Test
	public void case2(String browser) throws InterruptedException {
		
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browser.equals("safari")) {
			driver = new SafariDriver();
		}else {
			driver = new ChromeDriver();
		}
		
		System.out.println(browser);
//		System.out.println(url);
//		System.out.println(username);
//		System.out.println(password);
		
		Thread.sleep(1000);
		driver.quit();
		
	}

}
