package testBases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.github.javafaker.Faker;

import org.apache.logging.log4j.Logger; //Log4j
import org.apache.logging.log4j.LogManager; //Log4j

public class BaseTest {

	public static WebDriver driver;
	public Logger logger; // Log4j
	public Properties p; // properties class

	@BeforeClass(groups= {"Master", "Sanity", "Regression"})
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException 
	{
		
		//loading properties file
		 FileReader file=new FileReader(".//src//test//resources//Config.properties");
		 p=new Properties();
		 p.load(file);
		 
		// loading log4j file
		logger = LogManager.getLogger(this.getClass()); // return logs for a class

		// Selenium Grid execution
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser remote
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			String huburl = "http://192.168.1.101:4444/wd/hub";  // url + wd/hub
			driver = new RemoteWebDriver(new URL(huburl), capabilities); // to add remote driver
		}
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch (br.toLowerCase())
			{
			case "chrome":
				driver = new ChromeDriver();
				logger.info("Chrome browser");
				break;
			case "edge":
				driver = new EdgeDriver();
				logger.info("Edge browser");
				break;
			case "firefox":
				driver = new FirefoxDriver();
				logger.info("Firefox browser");
				break;
			default:
				System.out.println("Invalid browser name...");
				return;
			}
		}
		

		driver.manage().deleteAllCookies();
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL2")); // from config.properties file
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}

	@AfterClass(groups= {"Master", "Sanity", "Regression"})
	public void teardown() {
		driver.quit();
	}
	/*
	 * // Deprecated public String randomeString() { String
	 * generatedString=RandomStringUtils.randomAlphabetic(5); return
	 * generatedString; }
	 * 
	 * public String randomeNumber() { String
	 * generatedString=RandomStringUtils.randomNumeric(10); return generatedString;
	 * }
	 * 
	 * public String randomAlphaNumeric() { String
	 * str=RandomStringUtils.randomAlphabetic(3); String
	 * num=RandomStringUtils.randomNumeric(3);
	 * 
	 * return (str+"@"+num); }
	 */

	Faker faker = new Faker(); // to generate dummy data

	public String firstname() {
		String firstName = faker.name().firstName();
		return firstName;
	}

	public String lastname() {
		String lastName = faker.name().lastName();
		return lastName;
	}

	public String email() {
		faker.internet().emailAddress();

		String email = faker.internet().emailAddress();
		return email;
	}

	public String password() {
		String password = faker.internet().password(8, 12);
		return password;
	}

	public String telephone() {
		String telephone = faker.phoneNumber().cellPhone();
		return telephone;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

	/*
	 * String firstName = faker.name().firstName(); String lastName =
	 * faker.name().lastName(); String password = faker.internet().password(8, 12);
	 * String mobile = faker.phoneNumber().cellPhone(); return mobile;
	 */

}
