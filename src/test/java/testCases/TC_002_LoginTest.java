package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBases.BaseTest;

public class TC_002_LoginTest extends BaseTest {
	
	@Test(groups= {"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("**** Starting TC_002_LoginTest  ****");
		logger.debug("capturing application debug logs....");
		try
		{
			//Home page
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on myaccount link on the home page..");
			hp.clickLogin(); //Login link under MyAccount
			logger.info("clicked on login link under myaccount..");
			
			
			//Login page
			LoginPage lp=new LoginPage(driver);
			logger.info("Entering valid email and password..");
			lp.set_email(p.getProperty("email"));
			lp.set_password(p.getProperty("password"));
			lp.clickLogin(); //Login button
			logger.info("clicked on login button..");
			
			//My Account Page
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetPage = myacc.isMyAccountPageExists();
			Assert.assertEquals(targetPage, true, "Login Failed");
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		finally
		{
			logger.info("**** Finished TC_002_LoginTest  ****");
		}
	}

}
