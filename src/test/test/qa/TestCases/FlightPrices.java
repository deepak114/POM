package qa.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import qa.Pages.FlightResultPage;
import qa.Pages.HomePage;
import qa.Report.LogStatus;

import java.util.Map;
import java.util.Random;


import org.testng.annotations.DataProvider;

public class FlightPrices {
	//Returns Flight price in Long from String prices
	public Long numericalPrice(String Price)
	{
		Long NumPrice=Long.parseLong(Price.replaceAll("[^\\d.]", ""));
		return NumPrice;		
	}

	//Validates flight prices
	
	@Test(dataProvider="dp")
	public void FlightPrice(String ret, String dep)
	{
		FlightResultPage pageObj=HomePage.obj;
		Map<String, String> prices=pageObj.selectRandomFlight(Integer.parseInt(ret), Integer.parseInt(dep));
		SoftAssert softAssert=new SoftAssert();

		AssertJUnit.assertEquals(numericalPrice(prices.get("Dep Flight")), numericalPrice(prices.get("Dep Bottom Price")));
		
		AssertJUnit.assertEquals(numericalPrice(prices.get("Ret Flight")), numericalPrice(prices.get("Ret Bottom Price")));
		
		Long expectedTotal=numericalPrice(prices.get("Dep Bottom Price"))+numericalPrice(prices.get("Ret Bottom Price"));
		
		AssertJUnit.assertEquals(numericalPrice(prices.get("total Price")),expectedTotal);
		
		softAssert.assertAll();
		LogStatus.pass("Price is matching "+numericalPrice(prices.get("Dep Flight"))+ " and "+ numericalPrice(prices.get("Dep Bottom Price")));
		LogStatus.pass("Price is matching "+numericalPrice(prices.get("Ret Flight"))+ " and "+ numericalPrice(prices.get("Ret Bottom Price")));
		LogStatus.pass("Price is matching "+numericalPrice(prices.get("total Price"))+ " and "+ expectedTotal);
	}
	//Provides random integer for flight price validations
	//it provides int for top 10 flights if number of flight is more than 10 else it selects between 0 to number of flight
	@DataProvider
	public String[][] dp() throws Exception
	{
		String[][] data= new String[3][2];
		Random rand=new Random();
		FlightResultPage pageObj=HomePage.obj;
		pageObj.clearFilter();
		int Depcount=pageObj.departureFilightCount();
		int retCount=pageObj.returnFilightCount();
		for(int i=0;i<3;i++)
		{
			
			
			if(Depcount>10)
			{
				data[i][0]=rand.nextInt(11)+"";
			}
			else
			{
				data[i][0]=rand.nextInt(Depcount)+"";
			}
			if(retCount>10)
			{
				data[i][1]=rand.nextInt(11)+"";
			}
			else
			{
				data[i][1]=rand.nextInt(retCount)+"";
			}
			
		}
		
		return data;
	}
}
