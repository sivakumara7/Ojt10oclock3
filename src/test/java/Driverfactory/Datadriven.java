package Driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Applicationlayer.Loginpage;
import Applicationlayer.supplierpage;
import Utilities.Excelfileutil;

public class Datadriven {
	WebDriver driver;
	String inputpath="E:\\10'Oclock OJT\\ERP_Maven\\TestInput\\Testdata5.xlsx";
	String outputpath="E:\\10'Oclock OJT\\ERP_Maven\\TestOutput\\Hybrid.xlsx";
	@BeforeTest
	public void setup() throws Throwable
	{
		System.setProperty("wbbdriver.gecko.driver", "E:\\10'Oclock OJT\\ERP_StockAccounting\\commondrivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.navigate().to("http://projects.qedgetech.com/webapp/login.php");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		//call login page
		Loginpage login=PageFactory.initElements(driver, Loginpage.class);
	String result=login.verifyLogin("admin", "master");
		Reporter.log(result,true);
	}
	@Test
	public void supplier() throws Throwable
	{
		//call supplier page class
		supplierpage supplier=PageFactory.initElements(driver, supplierpage.class);
		//create object for excelfileutil
		Excelfileutil xl=new Excelfileutil();
		//count no of rows in Supplier sheet
		int rc=xl.rowCount("Supplier");
		Reporter.log("No of rows::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			//read all columns from Supplier sheet
			String sname=xl.getCellData("Supplier", i, 0);
			String address=xl.getCellData("Supplier", i, 1);
			String city=xl.getCellData("Supplier", i, 2);
			String country=xl.getCellData("Supplier", i, 3);
			String cperson=xl.getCellData("Supplier", i, 4);
			String pnumber=xl.getCellData("Supplier", i, 5);
			String mail=xl.getCellData("Supplier", i, 6);
			String mnumber=xl.getCellData("Supplier", i, 7);
			String note=xl.getCellData("Supplier", i,8 );
		String results	=supplier.verifySupplier(sname, address, city, country, cperson, pnumber, mail, mnumber, note);
		Reporter.log(results,true);
		//write into results
		xl.setCellData("Supplier", i, 9, results, outputpath);
		}
				
			}
	@AfterMethod
	public void tearDown()
	{
		driver.close();
		}
	}


