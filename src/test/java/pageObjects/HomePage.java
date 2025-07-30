package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	
	//constructor 
	public HomePage(WebDriver driver){
		super(driver);
	}
	//Locators 
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnk_Myaccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnk_Registration;
	@FindBy(xpath="//a[normalize-space()='Login']" ) WebElement lnk_login;
	
	//Action method 
	
	public void clickMyAccount()
	{
		lnk_Myaccount.click();
	}
	
	public void clickRegistration()
	{
		lnk_Registration.click();
	}
	
	public void clickLogin()
	{
		lnk_login.click();
	}

}
