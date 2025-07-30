package pageObjects;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	//constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	//locators
	@FindBy(xpath = "//h2[text()='My Account']") // MyAccount Page heading
	WebElement msgHeading;
	
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	WebElement lnkLogout;
	
	
	//action methods
	public boolean isMyAccountPageExists()   // MyAccount Page heading display status
	{
		try 
		{
			return msgHeading.isDisplayed(); //true
		}
		catch(Exception e)
		{
			return false;
		}
 
	}
	
	
	public void clickLogout() {
		lnkLogout.click();

	}
	
}
