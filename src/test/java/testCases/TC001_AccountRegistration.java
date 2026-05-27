package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.RegistrationPage;
import testBase.BaseTest;

public class TC001_AccountRegistration extends BaseTest {

	@Test(groups="Smoke")
	public void register_account() throws InterruptedException {

		logger.info("*****TC001_AccountRegistration test started");

		try {
			LandingPage lp = new LandingPage(driver);
			lp.clickMyAccount();
			lp.clickRegister();
			Thread.sleep(2000);
			logger.info("**Clicked on MyAccount -> Register");

			RegistrationPage rp = new RegistrationPage(driver);
			rp.setFName(randomString().toUpperCase());
			rp.setLName(randomString().toUpperCase());
			rp.setEmail(randomString() + "@gmail.comm");
			
			//System.out.println(rp.getUserName());
			//System.out.println(rp.getPwd());
	
			rp.setPhone("1234567890");
			rp.setPassword("pass1234");
			rp.getPwd();
			rp.setConPassword("pass1234");

			rp.clickAccept();
			
			rp.getUserName();
			rp.clickContinue();
			
		
		

			logger.info("**Clicked on Contine button");

			logger.info("**Validating Expected message");
			Thread.sleep(2000);
			String confmsg = rp.getConfirmation();
		
			
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");
			
	

		} catch (Exception e) {

			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		
		logger.info("*** Test Execution completed***");
	}
}
