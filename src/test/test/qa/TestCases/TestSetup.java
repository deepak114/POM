package qa.TestCases;

import qa.TestBase.TestBase;
import qa.Report.ExtentReport;

import org.testng.annotations.BeforeSuite;

import org.testng.annotations.AfterSuite;

public class TestSetup {
 //Initializes browser and reports
  @BeforeSuite
  public void beforeSuite() 
  {
	  ExtentReport.initialize();
	  
	  ExtentReport.logger= ExtentReport.report.startTest("Started");
	  TestBase.initialize();
	  

  }
//closed browser and report object
  @AfterSuite
  public void afterSuite() 
  {
	  TestBase.quit();
	  ExtentReport.report.flush();
	  
  }

}
