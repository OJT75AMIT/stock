package CommonFunctionLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class StockAccounting 
{
	WebDriver driver;
	String res;
// applaunch
	
	public String appLaunch(String url)
	{
		System.setProperty("webdriver.chrome.driver","D:\\Amit\\StockAccounting\\CommonJarFiles\\chromedriver.exe");
		 driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		
		//validation
		
		if (driver.findElement(By.id("username")).isDisplayed())
		{
		res="pass";	
		}
		else
		{
			res="fail";
			
		}
		return res;
	}
	
//appLogin
	public String appLogin(String username,String password)
	{
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);

		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("btnsubmit")).click();
		//validation
		if (driver.findElement(By.id("logout")).isDisplayed()) {
			res="Pass";
			
		} else {
            res="Fail";
		}
		 return res;
		
	}
	
	//appLogout
	public String appLogout() throws Throwable
	{
		Thread.sleep(2000);
		driver.findElement(By.id("logout")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			res="Pass";
		}else
		{
			res="Fail";
		}
	    
		return res;
	
	
	}
	
	//appclose
	public void appClose()
	{
		driver.close();
	}
	
    //Suppliercreation
	
	public String suppliercreation(String sName,String add,String city,String country,String contactPerson,String phoneNum,String email,String mobileNum,String notes) throws Throwable
	{
		driver.findElement(By.id("mi_a_suppliers")).click();
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")).click();
		String exp_data=driver.findElement(By.id("x_Supplier_Number")).getAttribute("value");
		System.out.println(exp_data);
		driver.findElement(By.id("x_Supplier_Name")).sendKeys(sName);
		driver.findElement(By.id("x_Address")).sendKeys(add);
		driver.findElement(By.id("x_City")).sendKeys(city);
		driver.findElement(By.id("x_Country")).sendKeys(country);
		driver.findElement(By.id("x_Contact_Person")).sendKeys(contactPerson);
		driver.findElement(By.id("x_Phone_Number")).sendKeys(phoneNum);
		driver.findElement(By.id("x__Email")).sendKeys(email);
		driver.findElement(By.id("x_Mobile_Number")).sendKeys(mobileNum);
		driver.findElement(By.id("x_Notes")).sendKeys(notes);
		//scroll down page
		Actions action=new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.id("btnAction")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='OK!' ]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		Thread.sleep(2000);
		//validation
		if (driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).isDisplayed()) 
		{
			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(exp_data);
			Thread.sleep(2000);
			driver.findElement(By.id("btnsubmit")).click();
			
		} else {
			Thread.sleep(2000);
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(exp_data);
			driver.findElement(By.id("btnsubmit")).click();
			  
		}
		
		String act_data=driver.findElement(By.xpath("//*[@id='el1_a_suppliers_Supplier_Number']/span")).getText();
		System.out.println(act_data);
		if (exp_data.equals(act_data))
		{
			
			res="pass";
		} else
		{
           res="fail";
		}
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
		return res;
	}
	
	
	
	
	/*
	public static void main(String[] args) throws Throwable {
		
		StockAccounting app=new StockAccounting();
		System.out.println( app.appLaunch("http://webapp.qedge.com"));
	    System.out.println(app.appLogin("admin","master"));
	    System.out.println(app.suppliercreation("amit", "ammeerpet", "hyderad", "india", "amit", "7777", "ahsknj", "9999", "fine"));
	    
		System.out.println(app.appLogout());
		app.appClose();
	}*/
}
