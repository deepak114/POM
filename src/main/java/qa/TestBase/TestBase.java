package qa.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import qa.Listners.EventHandler;
import qa.Report.LogStatus;


public class TestBase {
	
	public static WebDriver driver;
	
	private TestBase()
	{
		String browser=TestBase.get("Browser");
		String headless=TestBase.get("HeadlessMode");
		String imageDisable=TestBase.get("DisableImage");
		if(browser.equalsIgnoreCase("chrome")|| browser.toUpperCase().contains("CHROME"))
		{
			try{
				
				System.setProperty("webdriver.chrome.driver",TestBase.getPath(browser));
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--incognito");
				if(imageDisable.equalsIgnoreCase("yes"))
				{
					TestBase.disableImg(options);
				}
				/*if(headless.equalsIgnoreCase("yes"))
				{
					new HeadlessMode().headless(options);
				}*/
				DesiredCapabilities capabilites=DesiredCapabilities.chrome();
				capabilites.setCapability(ChromeOptions.CAPABILITY, options);
				driver=new ChromeDriver(options);
				
				LogStatus.pass("Chrome drive launched with headless mode = "+headless.toUpperCase()+", Image Disable mode = "+imageDisable.toUpperCase());
								
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (browser.equalsIgnoreCase("FF")|| browser.toUpperCase().contains("FIRE")) 
		{
			try
			{
				
				System.setProperty("webdriver.gecko.driver",TestBase.getPath(browser));
				FirefoxOptions FFoptions=new FirefoxOptions();
				if(imageDisable.equalsIgnoreCase("yes"))
				{
					TestBase.disableImg(FFoptions);
				}
				if(headless.equalsIgnoreCase("yes"))
				{
					 TestBase.headless(FFoptions);
				}
				
				driver=new FirefoxDriver(FFoptions);
				
				
				LogStatus.pass("FF drive launched with headless mode = "+headless.toUpperCase()+", Image Disable mode = "+imageDisable.toUpperCase());
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				LogStatus.fail(e);
			}
		}
		
	}
	
	//Function to read the property file and load into application
	public static String get(String PropertyName)
	{
		String returnProperty="";
		Properties property = new Properties();
		try {
			FileInputStream file =
					new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//TestRunDetails.properties");
			property.load(file);
			returnProperty=property.getProperty(PropertyName);
			if(returnProperty==null)
			{
				throw new Exception("Property with name : "+PropertyName+" not found in "+System.getProperty("user.dir")+"\\src//main//resources//TestRunDetails.properties Please check again");
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnProperty;
	}

// Function to set the browser executable
	
	public static String getPath(String browser) 
	{
		String OS=System.getProperty("os.name");
		String driverPath=null;
		if(OS.toUpperCase().contains("Windows"))
		{
			if(browser.toUpperCase().contains("CHROME"))
			{
				driverPath=".//src//main//resources//chromedriver.exe";
			}
			else if(browser.toUpperCase().contains("FF")||browser.toUpperCase().contains("FIRE"))
			{
				driverPath=".//src//main//resources//geckodriver.exe";
				
			}
		}
		else if(OS.toUpperCase().contains("MAC"))
		{
			if(browser.toUpperCase().contains("CHROME"))
			{
				driverPath=".//src//main//resources//chromedriver";
			}
			else if(browser.toUpperCase().contains("FF")||browser.toUpperCase().contains("FIRE"))
			{
				driverPath=".//src//main//resources//geckodriver";
			}
		}
		else
		{
			if(browser.toUpperCase().contains("CHROME"))
			{
				driverPath="/home/deepak/Jars/chromedriver";
			}
			else if(browser.toUpperCase().contains("FF")||browser.toUpperCase().contains("FIRE"))
			{
				driverPath="home/deepak/Jars/geckodriver";
			}
		}
		return driverPath;
	}

	// function to set up head less options
	
	public static void headless(ChromeOptions options)
	{
		options.addArguments("window-size=1400,800");
		options.addArguments("headless");
	}
	//Configures FireFox to run in headless mode
	public static void headless(FirefoxOptions options)
	{
		FirefoxBinary firefoxBinary=new FirefoxBinary();
		firefoxBinary.addCommandLineOptions("--headless");
		options.setBinary(firefoxBinary);
	}
	public static void disableImg(ChromeOptions options)
	{
		HashMap<String, Object> images=new HashMap<String, Object>();
		images.put("images", 2);
		HashMap<String, Object> pref=new HashMap<String, Object>();
		pref.put("profile.default_content_setting_values", images);
		options.setExperimentalOption("prefs", pref);
	}
	//disables images in Firefox browser
	public static void disableImg(FirefoxOptions options)
	{
		FirefoxProfile profile=new FirefoxProfile();
		profile.setPreference("permissions.default.image", 2);
		options.setProfile(profile);
		options.setCapability(FirefoxDriver.PROFILE, profile);
	}
	public static void initialize()
	{
		new TestBase();
	}
	//quits browser
	public static void quit()
	{
		driver.quit();
	}
	//initializes WebDriver EventListner
	public void EventHandlerInit()
	{
		EventFiringWebDriver eventHandle=new EventFiringWebDriver(driver);
		EventHandler handler=new EventHandler();
		eventHandle.register(handler);
		driver=eventHandle;
	}
	

}
