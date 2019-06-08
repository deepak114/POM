package qa.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;


import qa.Pages.HomePage;

public class SearchFlightTest {
//Searches flight by providing FROM,TO , DEPARTURE and RETURN details
  @Test
  public void searchFlights() 
  {
	 HomePage homePage= new HomePage();
	 homePage.Search();
  }
}
