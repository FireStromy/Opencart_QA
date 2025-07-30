package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBases.BaseTest;
import utilities.DataProviders;

/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC_003_LoginDDT extends BaseTest{
	
	@Test(dataProvider= "LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") // linking dp
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("**** Starting TC_003_LoginDDT *****");
		
		try
		{
			//Home page
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin(); //Login link under MyAccount
				
			//Login page
			LoginPage lp=new LoginPage(driver);
			lp.set_email(email);
			lp.set_password(pwd);
			lp.clickLogin(); //Login button
				
			//My Account Page
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage=macc.isMyAccountPageExists();
			
			if(exp.equalsIgnoreCase("Valid")) /// if valid 
			{
				if(targetPage==true)
				{
					macc.clickLogout();               //should pass means login
					Assert.assertTrue(true);   // test pass
				}
				else
				{
					Assert.assertTrue(false);       //test fail
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid")) //if invalid
			{
				if(targetPage==true)
				{
					macc.clickLogout();     //  logout
					Assert.assertTrue(false);   // test fail
				}
				else
				{
					Assert.assertTrue(true);   //    test pass   //should fail means not login
				}
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("An exception occurred: " + e.getMessage());
		
		}
		
		logger.info("**** Finished TC_003_LoginDDT *****");
	}

}
