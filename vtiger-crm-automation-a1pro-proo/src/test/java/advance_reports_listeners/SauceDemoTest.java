package advance_reports_listeners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(advance_reports_listeners.List_Imp.class)
public class SauceDemoTest {

	@Test
	public void case1() throws InterruptedException {
		Assert.assertTrue(true);
	}

	@Test
	public void case2() throws InterruptedException {
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "case2")
	public void case3() throws InterruptedException {
		Assert.assertTrue(true);
	}

}
