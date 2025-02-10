package driverFactory;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commmonFunctions.FunctionLibrary;
import config.AppUtil;
import util.ExcelUtil;


public class AppTest  extends AppUtil{
	String inputpath ="./FileInput/TestData.xlsx";
	String outputpath ="./FileOutput/DataDrivernResults.xlsx";
	String TCSheet ="LoginData";
	@Test
	public void startTest() throws Throwable
	{
		//create obejct for Excelutil class
		ExcelUtil xl = new ExcelUtil(inputpath);
		//count no of rows in logindata sheet
		int rc =xl.rowCount(TCSheet);
		Reporter.log("no of rows are:::"+rc,true);
		//iterate all rows in Logindata sheet
		for(int i=1;i<=rc;i++)
		{
			//calling username cell and password cell
			String username = xl.getCellData(TCSheet, i, 0);
			String password =xl.getCellData(TCSheet, i, 1);
			//call admin login method from function libaray
			boolean res =FunctionLibrary.adminLogin(username, password);
			if(res)
			{
				//if res is true write as valid credentials into results cell
				xl.setCellData(TCSheet, i, 2, "valid credentials", outputpath);
				//write as pass into status cell
				xl.setCellData(TCSheet, i, 3, "Pass", outputpath);
			}
			else
			{
				//take screen shot
				File screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//copy screen shot into local system
				FileUtils.copyFile(screenshot, new File("./target/Iteration/"+i+"   "+"Loginpage.png"));
				//if res is false write as invalid credentials into results cell
				xl.setCellData(TCSheet, i, 2, "invalid credentials", outputpath);
				//write as Fail into status cell
				xl.setCellData(TCSheet, i, 3, "Fail", outputpath);
			}
		}
		
		
	}
	

}
