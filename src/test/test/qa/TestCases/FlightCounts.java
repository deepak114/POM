package qa.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import qa.Pages.FlightResultPage;
import qa.Pages.HomePage;
import qa.Report.LogStatus;



public class FlightCounts {
	
	public static FlightResultPage pageObj=null;
	int[] counts;
	//Prints flight number when no filter is selected
  @Test(priority=1)
  public void NoFilterFlightCounts() throws Exception 
  {
	  pageObj=HomePage.obj;
	  counts=pageObj.NoFilterFlightCount();
	  System.out.println("*****Flight Count when No checkbox are selected*****");
	  System.out.println("Depature Flght count:"+counts[0]+"\nReturn Flight Count"+counts[1]);
	  LogStatus.pass("Depature Flght count:"+counts[0]+"\nReturn Flight Count"+counts[1]);
	 
	  
  }
  //prints flight when Non stop filter is selected
  @Test(priority=2)
  public void NoStopsFlightCount() throws Exception
  {
	  pageObj=HomePage.obj;
	  System.out.println("*****Flight Count when Non Stop checkbox is selected*****");
	  counts=pageObj.NoStopFlightCount();
	  System.out.println("Depature Flght count:"+counts[0]+"\nReturn Flight Count"+counts[1]);
	  LogStatus.pass("Depature Flght count:"+counts[0]+"\nReturn Flight Count"+counts[1]);
  }
  //Select flights when oneStop filter is selected
  @Test(priority=3)
  public void OnStopFlightCount() throws Exception
  {
	  pageObj=HomePage.obj;
	  System.out.println("*****Flight Count when 1 Stop checkbox is selected*****");
	  counts=pageObj.oneStopFlightCount();
	  System.out.println("Depature Flght count:"+counts[0]+"\nReturn Flight Count"+counts[1]);
	  LogStatus.pass("Depature Flght count:"+counts[0]+"\nReturn Flight Count"+counts[1]);
  }
}
