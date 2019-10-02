package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctionLibrary.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	public void startTest() throws Throwable
	{
		
		//create object for reuse excel methodes
		ExcelFileUtil excel= new ExcelFileUtil();
		//working with "masterTestcases" sheet
		for (int i = 1; i <=excel.rowCount("MasterTestCases"); i++)
		{
			if (excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				//Define module(test Cases ) name
				String TCModule=excel.getData("MasterTestCases",i,1);
				String moduleStatus="";
				report =new ExtentReports("D"
				+ ":\\Amit\\StockAccounting\\Reports"+TCModule+FunctionLibrary.generateDate()+".html");
				logger=report.startTest(TCModule);
				// working with "TCModule(Test cases Steps)" Sheet
				int rowcount= excel.rowCount(TCModule);
				for (int j = 1; j <=rowcount; j++)
				{
				 String Description=excel.getData(TCModule, j, 0);	
				 String Object_Type=excel.getData(TCModule, j, 1);	
				 String Locator_Type=excel.getData(TCModule, j, 2);	
				 String Locator_Value=excel.getData(TCModule, j, 3);	
				 String Test_Data=excel.getData(TCModule, j, 4);
				try{ 
				 if (Object_Type.equalsIgnoreCase("startBrowser"))
				 {
					driver=FunctionLibrary.startBrowser(driver);
					logger.log(LogStatus.INFO, Description);
				 }
				 if (Object_Type.equalsIgnoreCase("openApplication"))
				 {
					FunctionLibrary.openApplication(driver); 
					logger.log(LogStatus.INFO, Description);
				 }
				 if (Object_Type.equalsIgnoreCase("typeAction"))
				 {
					FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
					logger.log(LogStatus.INFO, Description);
				 }
				 if (Object_Type.equalsIgnoreCase("waitForElement"))
				 {
					FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
					logger.log(LogStatus.INFO, Description);
				 }
				 if (Object_Type.equalsIgnoreCase("clickAction"))
				 {
					FunctionLibrary.clickAction(driver,Locator_Type ,Locator_Value );
					logger.log(LogStatus.INFO, Description);
				 }
				 if (Object_Type.equalsIgnoreCase("closebrowser"))
				 {
					FunctionLibrary.closeBrowser(driver);
					logger.log(LogStatus.INFO, Description);
				 
				  }
				 if(Object_Type.equalsIgnoreCase("pageDown"))
				 {
					 FunctionLibrary.pageDown(driver);
						logger.log(LogStatus.INFO, Description);
				 }
				 if(Object_Type.equalsIgnoreCase("tableValidation"))
				 {
					 FunctionLibrary.tableValidation(driver, "column");
						logger.log(LogStatus.INFO, Description);
				 }
				 
				 
				 excel.setData(TCModule, j, 5, "Pass");
					logger.log(LogStatus.PASS, Description);
				 moduleStatus="true";
				 
				}
				catch(Exception e){
					excel.setData(TCModule, j, 5, "Fail");
					logger.log(LogStatus.FAIL, Description);
					 moduleStatus="false";
					 File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					 FileUtils.copyFile(srcFile,new File("D:\\Amit\\StockAccounting\\Sreen Shot\\"+TCModule+FunctionLibrary.generateDate()+".png"));
					 
					 
					 break;
					 
				}
			}
				if(moduleStatus.equalsIgnoreCase("true"))
				{
					excel.setData("MasterTestCases", i, 3, "PASS");
				
				}else
					if(moduleStatus.equalsIgnoreCase("false"))
				{
					excel.setData("MasterTestCases", i, 3, "FAIL");
				
				}
				report.endTest(logger);
				report.flush();}
					
				else
				{
					excel.setData("MasterTestCases", i, 3, "NOT EXECUTED");
					
				}
		}
	}

}
