package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC002_AccountLogin extends BaseTest {

	@Test(groups={"Regression","Master"})
	public void verify_Login() throws InterruptedException {

		logger.info("*****TC001_AccountLogin test started");

		try {
			//HomePage
			LandingPage lp = new LandingPage(driver);
			lp.clickMyAccount();
			lp.clickLogin();
			Thread.sleep(2000);
			logger.info("**Clicked on MyAccount -> Login");

			//LoginPage
			LoginPage login = new LoginPage(driver);
			login.setEmail(prop.getProperty("user"));
			login.setPassword(prop.getProperty("password"));
			login.clickLoginButton();
			logger.info("**Clicked on Login button");
			logger.info("**Validating Expected message");
			Thread.sleep(2000);
			
			//MyAccountPage
			MyAccountPage mp = new MyAccountPage(driver);
			String confmsg = mp.getConfirmation();

			Assert.assertEquals(confmsg, "My Orders", "The label not amcthing");

		} catch (Exception e) {

			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}

		logger.info("*** Test Execution completed***");
	}
}
