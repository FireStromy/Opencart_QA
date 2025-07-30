package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBases.BaseTest;

public class TC_001_AccountRegistrationTest extends BaseTest {

	@Test(groups= {"Regression", "Master"})
	public void verify_account_registration() 
	{

		logger.info("***** Starting TC001_AccountRegistrationTest  ****");  // log
		logger.debug("This is a debug log message"); // log
		
		try 
		{
			
		
		HomePage hp = new HomePage(driver); // intiationg homepage driver
		hp.clickMyAccount(); // calling methods
		logger.info("Clicked on MyAccount Link.. ");   // log
		
		hp.clickRegistration();
		logger.info("Clicked on Register Link.. "); // log
		
		RegistrationPage repage = new RegistrationPage(driver);
		
		logger.info("Providing customer details...");  // log
		repage.setFirstName(firstname());
		repage.setLastName(lastname());
		repage.setEmail(email());
		repage.setTelephone(telephone());
		
		String password = password(); // need to use same pass
		repage.setPassword(password);
		repage.setConfirmPassword(password);
		
		repage.setPrivacyPolicy();
		repage.clickContinue();
		
		logger.info("Validating expected message.."); // log
		
		String confmsg=repage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
			logger.info("Test passed");
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) 
		{
			
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		finally 
		{
			logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}
		
		
		
	}
}
