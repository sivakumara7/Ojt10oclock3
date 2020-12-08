package Applicationlayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Loginpage {
WebDriver driver;
WebDriverWait wait;
public void Login(WebDriver driver)
{
	this.driver=driver;
}
//maintain OR for Login
@FindBy(xpath="//*[@id=\"username\"]")
WebElement enterusername;
@FindBy(xpath="//*[@id=\"password\"]")
WebElement enterpassword;
@FindBy(xpath="//*[@id=\"btnsubmit\"]")
WebElement clickLogin;
@FindBy(linkText="Logout")
WebElement logoutlink;
public String verifyLogin(String username,String password)
{
	wait=new WebDriverWait(driver, 30);
	String res=null;
	wait.until(ExpectedConditions.visibilityOf(enterusername));
	this.enterusername.clear();
	this.enterusername.sendKeys(username);
	//wait password textbox
	wait.until(ExpectedConditions.visibilityOf(enterpassword));
	this.enterpassword.clear();
	this.enterpassword.sendKeys(password);
	//wait until login button clickable
	wait.until(ExpectedConditions.elementToBeClickable(clickLogin));
	this.clickLogin.click();
	//wait until logout link is visible
	wait.until(ExpectedConditions.visibilityOf(logoutlink));
	
	if(logoutlink.isDisplayed())
	{
		res="Login Success";
	}
	else
	{
		res="Login Fail";
	}
	return res;
}
}
