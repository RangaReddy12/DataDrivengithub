package commmonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
//method for login
public static boolean adminLogin(String user,String pass) throws Throwable
{
	driver.get(conpro.getProperty("url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
	driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
	driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();
	Thread.sleep(3000);
	String Expected ="dashboard";
	String Actual = driver.getCurrentUrl();
	if(Actual.contains(Expected))
	{
		Reporter.log("Login Success",true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail",true);
		return false;
	}
}
}
