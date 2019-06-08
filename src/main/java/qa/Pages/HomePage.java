package qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.TestBase.TestBase;
import qa.Util.DatePicker;
import qa.Util.DynamicXpath;
import qa.Util.Wait;


public class HomePage {

	@FindBy(xpath="//a[contains(@href,'flights') and contains(@class,'hrtlCenter')]")
	WebElement FlightsTab;
	
	@FindBy(xpath="//li[contains(text(),'Round Trip')]/child::span")
	WebElement RoundTrip;
	
	@FindBy(xpath="//input[@id='fromCity']")
	WebElement FromCity;
	
	@FindBy(xpath="//input[@placeholder='From']")
	WebElement FromCityEnter;
		
	@FindBy(xpath="//input[@placeholder='To']")
	WebElement ToCityEnter;
	
	@FindBy(xpath="//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']")
	WebElement autosuggestFirstOption;
	
	@FindBy(xpath="//label[@for='departure']")
	WebElement departureDate;
	
	@FindBy(xpath="//label[@for='return']")
	WebElement returnDate;
			
	@FindBy(xpath="//a[@class='primaryBtn font24 latoBlack widgetSearchBtn ']")
	WebElement search;
	
	
	String datePickerXpath="//div[@aria-label='%replacable%' and @aria-disabled='false']";
	String TripTypexpath="//li[contains(text(),'%replacable%')]/child::span";
	
	public static FlightResultPage obj=null;
	//Constructor to initialize webelements
	public HomePage()
	{
		PageFactory.initElements(TestBase.driver, this);
	}
	
	//Searches for flight by entering FROM,TO, DEPARTURE and RETURN details, clicks on search and returns object of flight result page
	public FlightResultPage Search() 
	{
		FlightsTab.click();
		TestBase.driver.findElement(DynamicXpath.get(TripTypexpath, TestBase.get("Trip"))).click();
		FromCity.click();
		
		FromCityEnter.sendKeys(TestBase.get("From"));
		Wait.toBeclickable2(autosuggestFirstOption, TestBase.get("From"));
		autosuggestFirstOption.click();
		ToCityEnter.sendKeys(TestBase.get("To"));
		departureDate.click();
		DatePicker date=DatePicker.getDates();
		TestBase.driver.findElement(DynamicXpath.get(datePickerXpath, date.getDepartureDate())).click();
		TestBase.driver.findElement(DynamicXpath.get(datePickerXpath, date.getReturnDate())).click();
		search.click();
		return HomePage.obj=new FlightResultPage();
	}
}
