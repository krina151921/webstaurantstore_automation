package utilityFunctions;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InitializeDriver {
public static WebDriver driver;

public WebDriver getDriver() throws IOException{
	
		PropertiesConfig pr=new PropertiesConfig();
		Properties p=pr.getProerty();
			// TODO Auto-generated method stub
		// Get the browser property from .properties file	
		    String strBrowser=p.getProperty("browser");
		    
		  if(driver==null){
			if(strBrowser.equalsIgnoreCase("IE")){
				System.setProperty("webdriver.ie.driver",p.getProperty("IEDriverPath"));
				driver=new InternetExplorerDriver();
			}else if (strBrowser.equalsIgnoreCase("CHROME")){
				System.setProperty("webdriver.chrome.driver",p.getProperty("ChromeDriverPath"));
			       ChromeOptions ops = new ChromeOptions();
	    	       ops.addArguments("--disable-notifications");
	               driver = new ChromeDriver(ops);
			}else if (strBrowser.equalsIgnoreCase("FIREFOX")){
				System.setProperty("webdriver.gecko.driver",p.getProperty("GeckoDriverPath"));
				 driver=new FirefoxDriver();
			}
			// to maximize the window
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			
		  }
			
			return driver;
}
}
