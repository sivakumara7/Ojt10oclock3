package Applicationlayer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class supplierpage {
	WebDriver driver;
	WebDriverWait wait;
	public void SupplierPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath="/html/body/div[2]/div[2]/div/div/ul[1]/li[3]/a")
	WebElement clicksupplier;
	@FindBy(xpath="/html/body/div[2]/div[3]/div/div/div[3]/div[1]/div[1]/div[1]/div/a/span")
	WebElement clinkadd;
	@FindBy(xpath="//*[@id=\"x_Supplier_Number\"]")
	WebElement snumber;
	@FindBy(xpath="//*[@id=\"x_Supplier_Name\"]")
	WebElement entersname;
	@FindBy(xpath="//*[@id=\"x_Address\"]")
	WebElement enterAddress;
	@FindBy(xpath="//*[@id=\"x_City\"]")
	WebElement entercity;
	@FindBy(xpath="//*[@id=\"x_Country\"]")
	WebElement entercountry;
	@FindBy(xpath="//*[@id=\"x_Contact_Person\"]")
	WebElement enterperson;
	@FindBy(xpath="//*[@id=\"x_Phone_Number\"]")
		WebElement enterpnumber;
	@FindBy(xpath="//*[@id=\"x__Email\"]")
	WebElement enterEmail;
	@FindBy(xpath="//*[@id=\"x_Mobile_Number\"]")
	WebElement entermnumber;
	@FindBy(xpath="//*[@id=\"x_Notes\"]")
	WebElement enterNotes;
	@FindBy(xpath="//*[@id=\"btnAction\"]")
	WebElement clinkadd1;
	@FindBy(xpath="/html/body/div[17]/div[2]/div/div[4]/div[2]/button[1]")
	WebElement clickok;
	@FindBy(xpath="/html/body/div[17]/div[2]/div/div[4]/div[2]/button")
	WebElement clickalertok;
	
	@FindBy(xpath="/html/body/div[2]/div[3]/div/div/div[2]/div[2]/div/button/span")
	WebElement  clicksearchpanel;
	@FindBy(xpath="//*[@id=\"psearch\"]")
	WebElement searchpanel;
	@FindBy(xpath="/html/body")
	WebElement clicksearch;
	@FindBy(xpath="/html/body/div[2]/div[3]/div/div/div[3]/form/div/div[2]/table/tbody/tr/td[1]/div/span/input")
	WebElement table;
	public String verifySupplier(String sname,String addes,String city,String country,String cperson,
			String pnumber,String email,String mnumber,String notes) throws Throwable
	{
		String res=null;
		wait=new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(clicksupplier));
	this.clicksupplier.click();
	wait.until(ExpectedConditions.elementToBeClickable(clinkadd));
	this.clinkadd.click();
	wait.until(ExpectedConditions.visibilityOf(snumber));
	//capture snumber into expected variable
	String suppliernumber=this.snumber.getAttribute("value");
	this.entersname.sendKeys(sname);
	this.enterAddress.sendKeys(addes);
	this.entercity.sendKeys(city);
	this.entercountry.sendKeys(country);
	this.enterperson.sendKeys(cperson);
	this.enterpnumber.sendKeys(pnumber);
	this.enterEmail.sendKeys(email);
	this.entermnumber.sendKeys(mnumber);
	this.enterNotes.sendKeys(notes);
	this.clinkadd1.click();
	wait.until(ExpectedConditions.elementToBeClickable(clickok));
	this.clickok.click();
	wait.until(ExpectedConditions.elementToBeClickable(clickalertok));
	this.clickalertok.click();
	Thread.sleep(4000);
	if(!searchpanel.isDisplayed())
	this.clicksearchpanel.click();
	Thread.sleep(4000);
	this.searchpanel.clear();
	this.searchpanel.sendKeys(suppliernumber);
	this.clicksearch.click();
	Thread.sleep(4000);
	List<WebElement> rows=table.findElements(By.tagName("tr"));
	System.out.println("No of rows are::"+rows.size());
	for(int i=1;i<=rows.size();i++)
	{
		//capture supplier number from supplier table
		WebElement  actual=driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/form/div/div[2]/table/tbody/tr[1]/td[6]/div/span/span"));
		Reporter.log(suppliernumber+"       "+actual,true);
		if(actual.equals(suppliernumber))
		{
			Reporter.log("supplier creation is succeess",true);
			res="pass";
		}
		else
		{
			Reporter.log("supplier creation fail",true);
			res="Fail";
		}
	}
		return res;
		
	}
	
	
	
	
	}


