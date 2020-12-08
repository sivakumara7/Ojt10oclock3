package Driverfactory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.FunctionLibrary;
import Utilities.Excelfileutil;

public class driverScript {
	WebDriver driver;
	String inputpath="E:\\10'Oclock OJT\\ERP_Maven\\TestInput\\Input.xlsx";
	String outputpath="E:\\10'Oclock OJT\\ERP_Maven\\TestOutput\\hybrid.xlsx";
	ExtentReports report;
	ExtentTest test;
	Logger log;
	public void startTest()
	{
		
	log=Logger.getLogger(getClass());
		PropertyConfigurator.configure("E:\\10'Oclock OJT\\ERP_StockAccounting1\\Propertyfile\\Log4j.properties");
		//creating excel object to accees excel utilities
		Excelfileutil xl=new Excelfileutil(inputpath);
		//iterate all rows in Mastertestcases sheet
		for(int i=1;i<=rowcount("MasterTestCases");i++)
		{
			String  moduleStatus="";
			if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
					{
				String TCModule=xl.getCellData("MasterTestCases", i, 1);
				//define path of extents Reports
				report=new ExtentReports("./ExtentReports/"+TCModule+FunctionLibrary.generateDate()+".html");
				//iterate all rows in TCModule
				for(int j=1;j<=rowcount(TCModule);j++)
				{
					
					test=report.startTest(TCModule);
					//read all cells from TCModule
					String Description=xl.getCellData(TCModule, j, 0);
					String Function_Name=xl.getCellData(TCModule, j, 1);
					String Locator_Type=xl.getCellData(TCModule, j, 2);
					String Locator_value=xl.getCellData(TCModule, j, 3);
					String Test_data=xl.getCellData(TCModule, j, 4);
					//calling the test step functions
					try {
						if(Function_Name.equalsIgnoreCase("startBrowser"))
						{
							driver=FunctionLibrary.startBrowser(driver);
							test.log(LogStatus.INFO, description);
							log.info("Executing startBrowser");
						}
						else if(Function_Name.equalsIgnoreCase("openApplication"))
						{
							FunctionLibrary.openApplication(driver);
							test.log(LogStatus.INFO, description);
							log.info("Executing openApplication");
						}else if(Function_Name.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitforElement(driver, Locator_Type, Locator_value, Test_Data);
							test.log(LogStatus.INFO, description);
							log.info("Executing waitForElement");
						}else if(Function_Name.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickaction(driver, Locator_Type, Locator_value);
							test.log(LogStatus.INFO, description);
							log.info("Executing clickAction");
							
						}else if(Function_Name.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, description);
							log.info("Executing closeBrowser");
						}
						//call catured data method
						else if(Function_Name.equalsIgnoreCase("captureData"))
						{
							FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, description);
							log.info("ExecutingcaptureData");
						}
						else if(Function_Name.equalsIgnoreCase("stablevalidation"))
						{
							FunctionLibrary.stableValidation(driver, Test_data);
							test.log(LogStatus.INFO, description);
							log.info("Executingstablevalidation");
						}
						//calling for stock table
						else if(Function_Name.equalsIgnoreCase("StockCategories"))
						{
							FunctionLibrary.stockCategories(driver);
							test.log(LogStatus.INFO, description);
							log.info("Executing StockCategories");
						}
						else if(Function_Name.equalsIgnoreCase(stablevalidation))
						{
							FunctionLibrary.stableValidation(driver, Test_data);
							test.log(LogStatus.INFO, description);
							log.info("Executingstablevalidation");
						}else if(Function_Name.equalsIgnoreCase("StockCategories"))
						{
							FunctionLibrary.stockCategories(driver);
							test.log(LogStatus.INFO, description);
							log.info("ExecutingStockCategories");
						}else if(Function_Name.equalsIgnoreCase("stablevalidation"))
						{
							FunctionLibrary.stableValidation(driver,Test_Data);
							test.log(LogStatus.INFO, description);
							log.info("Executingstablevalidation");
						}
						{
							
						}
						//write as pass into status cell in TCModule
						xl.setCellData(TCModule, j, 5, "pass", outputpath);
						test.log(LogStatus.PASS, description);
						
						moduleStatus="true";
						
						
					} catch (Exception e) 
					{
						System.out.println(e.getMessage());
						//write as fail into status cell in TCModule
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						test.log(LogStatus.FAIL, description);
						moduleStatus="false";
					}
					if(moduleStatus.equalsIgnoreCase("true"))
					{
						xl.setCellData("MasterTestCases", i, 3, "pass", outputpath);
					}
					if(moduleStatus.equalsIgnoreCase("false"))
					{
						xl.setCellData("MasterTestCases", i, 3, "Fail", outputpath);
					}
					report.endTest(test);
					report.flush();
				}
				
				
					}
			
		
		else
		{
			//write as not executed status into MasterTestCases sheet
			xl.setCellData("MasterTestCases", i, 3, "Not Executed", outputpath);
		}
	}

}
