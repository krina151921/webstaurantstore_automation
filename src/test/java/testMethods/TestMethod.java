package testMethods;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import utilityFunctions.InitializeDriver;
import utilityFunctions.PropertiesConfig;
import utilityFunctions.ScreenShotCapture;

public class TestMethod {
	
	String textToSearch="";
	WebDriver driver;

	@BeforeSuite
	public void setup() throws IOException{
		PropertiesConfig pc=new PropertiesConfig();
		Properties p=pc.getProerty();
		 textToSearch=p.getProperty("textToSearch");
		InitializeDriver id=new InitializeDriver();		
		driver=id.getDriver();	

	}
	
	
	@Test
	public void test1() throws IOException, InterruptedException{
		
			driver.get("https://www.webstaurantstore.com");
			driver.findElement(By.id("searchval")).sendKeys(textToSearch);
			driver.findElement(By.xpath("//button[@value='Search']")).click();
			ScreenShotCapture.capture("SearchResult");
			
			// navigate through all pages
			navigateAndValidateAllPages();
			
			Actions actions=new Actions(driver);
			List<WebElement> list_addButton=driver.findElements(By.xpath("//div[@id='product_listing']//input[@name='addToCartButton']"));
			
			//Click on add to Cart 
			
			actions.click(list_addButton.get(list_addButton.size()-1)).build().perform();
			
			// Click on View Cart
			if(driver.findElement(By.xpath("//a[@class='btn btn-small btn-primary']")).isDisplayed()){
				driver.findElement(By.xpath("//a[@class='btn btn-small btn-primary']")).click();
			}
				driver.findElement(By.xpath("//span[@class='btn btn-small btn-primary']//span[@class='menu-btn-text']")).click();
		
			ScreenShotCapture.capture("CartPage");
			// Click on delete button
			driver.findElement(By.xpath("(//a[@class='deleteCartItemButton close'])[1]")).click();
			Thread.sleep(2000);
			//Verify empty cart
			String text=driver.findElement(By.xpath("//p[@class='header-1']")).getText();
			Assert.assertEquals(text, "Your cart is empty.");
			ScreenShotCapture.capture("EmptyCart");
			driver.close();
	}
	
	public void navigateAndValidateAllPages(){
		List<WebElement> totalPages=driver.findElements(By.xpath("//a[contains(@href,'page')]"));
		int totalNumberOfPages= Integer.parseInt(totalPages.get(totalPages.size()-2).getText());
		System.out.println(totalNumberOfPages);
		Actions actions=new Actions(driver);
		for(int i=1;i<=totalNumberOfPages;i++){
			if(i!=1){
				actions.click(driver.findElement(By.xpath("//li//a[contains(text(),'"+i+"')]"))).build().perform();
			
			}
			List<WebElement> list=driver.findElements(By.xpath("//div[@id='product_listing']//a[@class='description']"));			
			for(WebElement element : list){			

			if(!element.getText().contains("Table")){
				System.out.println("Fail : Page : "+i+ " Product : "+element.getText()+" is not having word Table");
				Reporter.log("Fail : Page : "+i+ " Product : "+element.getText()+" is not having word Table");
			}
			
			}
		}
	}

}
