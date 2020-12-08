package CommonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;
import com.gargoylesoftware.htmlunit.javascript.host.file.FileReader;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;
	//method for launch browser
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"));
		{
			Reporter.log("Executing on firefoxbrowser",true);
			System.setProperty("webdriver.gecko.driver", "E:\\10'Oclock OJT\\ERP_Maven\\commondrivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			Reporter.log("Executing on chromebrowser",true);
			System.setProperty("webdriver.chrome.driver","E:\\10'Oclock OJT\\ERP_Maven\\commondrivers\\chromedriver.exe" );
			driver=new ChromeDriver();
		}else
		{
			System.out.println("Browser is not matching");
		}
		return webDriver;
	}
	//method for launching url
public static void openApplication(WebDriver driver) throws Throwable
{
	PropertyFileUtil.getValueForKey("url");
	driver.manage().window().maximize();

}
//method for wait for element
public static void waitforElement(WebDriver driver,String locatortype,String locatorvalue,String waittime)
{
	WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waittime));
if(locatortype.equalsIgnoreCase("name"))
{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
}
else if(locatortype.equalsIgnoreCase("id"))
{
	
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
}else if(locatortype.equalsIgnoreCase("xpath"))
{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
}
else
{
	System.out.println("unable to Execute waitforelement method");
}
}
//method for clickaction
public static void clickaction(WebDriver driver,String locatortype,String locatorvalue)
{
	if(locatortype.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(locatorvalue)).click();
	}else if(locatortype.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(locatorvalue)).sendKeys(Keys.ENTER);
	}else if(locatortype.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(locatorvalue)).click();
	}else
	{
		System.out.println("unable to execute click action method");
	}
}
//method for close browser
public static void closeBrowser(WebDriver driver)
{
	driver.close();
}
public static String generateDate()
{
	Date d=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
	return sdf.format(d);
}
//method for capture data
public static void captureData(WebDriver driver,String locatortype,String locatorvalue)
{
	String snumber="";
	if(locatortype.equalsIgnoreCase("id"))
	{
		 snumber=driver.findElement(By.id(locatorvalue)).getAttribute("value");
	}
	else if(locatortype.equalsIgnoreCase("name"))
	{
		 snumber=driver.findElement(By.name(locatorvalue)).getAttribute("value");
	}else if(locatortype.equalsIgnoreCase("xpath"))
	{
		 snumber=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
	}
	//write into notepad under captureddata folder
	File f=new File("E:\\10'Oclock OJT\\ERP_StockAccounting\\CaptureData\\supplier.txt");
	FileWriter fw=new FileWriter(f);
	BufferedWriter bw=new BufferedWriter(fw);
	bw.write(snumber);
	bw.flush();
	bw.close();
	
	
}
//supplier table validation
public static void stableValidation(WebDriver driver,String testdata)
{
	//read supplier number from notepad
	FileReader fr=new FileReader("E:\\\\10'Oclock OJT\\\\ERP_StockAccounting\\\\CaptureData\\\\supplier.txt");
	BufferedReader br=new BufferedReader(fr);
	String exp_data=br.readLine();
	//convert testdata into integer
	int column=Integer.parseInt(testdata);
	//if search textbox already exists then enter expdata into search textbox
	if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext")))
		//if not displayed click on search panel
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchpanel"))).click();
	Thread.sleep(4000);
	//clear text in search textbox
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext"))).clear();
	Thread.sleep(4000);
	//emnter expdata in searchtextbox
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext"))).sendKeys("");
	//click on searchbutton
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchbtn"))).click();
	Thread.sleep(4000);
	//store table into webelement
	WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("stable")));
	//get collection of rows in atable
	List<WebElement> rows=table.findElements(By.tagName("tr"));
	for(int i=1;i<=rows.size();i++)
	{
	String act_data=driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/form/div/div[2]/table/tbody/tr["+i+"]/td["+column+"]/div/span/span"));
	Assert.assertEquals(act_datal, exp_data,"Supplier number not found");
	Reporter.log(exp_data+"   "+act_data,true);
		break;
	}
		
			
}
//method mouse click
public static void stockCategories(WebDriver driver)
{
	Actions ac=new Actions(driver);
	ac.moveToElement(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/ul[1]/li[2]/a"))).perform();
	Thread.sleep(5000);
	ac.moveToElement(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/ul[1]/li[2]/ul/li[1]/a"))).click().perform();
	Thread.sleep(5000);
}
public static void sttableValidation(WebDriver driver,String testdata) throws Throwable
{
	if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext1"))).isDisplayed())
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtpanel1"))).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext1"))).clear();
	driver.findElement(By.xpath("searchtext1")).sendKeys("testdata");
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchbtn1"))).click();
	Thread.sleep(5000);
	WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("stable")));
	List<WebElement> rows=table.findElements(By.tagName("tr"));
	for(int i=1;i<=rows.size();i++)
	{
	String act_data=driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/form/div/div[2]/table/tbody/tr[+i+]/td[4]/div/span/span")).getText();
	Assert.assertEquals(act_data, testdata,"Stock category is Not matching");
	Reporter.log(act_data+"   "+testdata,true);
	break;
	}
}
}
