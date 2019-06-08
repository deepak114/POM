package qa.TestCases;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class FirstTestCase {
	public static WebDriver driver;
	public FirstTestCase()
	{
		super();
	}
	
	@Test
	public void first()
	{
		System.out.println("This is first class");
		String osName=System.getProperty("os.name");
		System.out.println(osName);
		System.setProperty("webdriver.chrome.driver", "/home/deepak/Jars/chromedriver");
		driver=new ChromeDriver();
	}
	
	@Test
	public void second()
	{
		System.out.println("This is second class");
	}
	
	@Test
	public void third()
	{
		System.out.println("This is third class");
	}

}
