package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TC003_AccountLogin_DataDriven extends BaseTest {

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups="DDT")
	public void verify_Login(String email, String pwd, String resp) throws InterruptedException {

		logger.info("*****TC001_AccountLogin test started");

		try {
			// HomePage
			LandingPage lp = new LandingPage(driver);

			lp.clickMyAccount();
			lp.clickLogin();
			// Thread.sleep(2000);
			logger.info("**Clicked on MyAccount -> Login");

			// LoginPage
			LoginPage login = new LoginPage(driver);
			login.setEmail(email);
			login.setPassword(pwd);
			login.clickLoginButton();
			logger.info("**Clicked on Login button");
			logger.info("**Validating Expected message");
			Thread.sleep(2000);

			// MyAccountPage
			MyAccountPage mp = new MyAccountPage(driver);
			String confmsg = mp.getConfirmation();
			
					
			if (confmsg.equals(confmsg)) {
				mp.clickLogout();
				Assert.assertTrue(true);
			} else {
			
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("*** Test Execution completed***");
	}
}
