package com.Ixigo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IxigoFlightBooking {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//starting browser 
		System.setProperty("webdriver.chrome.driver", "D:\\JavaSeleniumJar\\selenium jar\\chromedriver.exe");
		WebDriver driver  =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ixigo.com/");
		
		
		//clicking on round trip and handling stale elemnet exception
		Thread.sleep(3000);
		try {
	    driver.findElement(By.xpath("//*[text()='Round Trip']")).click();
		}catch (StaleElementReferenceException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		/// selecting  bombay shirdi city as autolist contains shirding as SAG
		WebElement elemtoAutopt = driver.findElement(By.xpath("//*[text()='To']/following::*[@placeholder= 'Enter city or airport'][1]"));
		elemtoAutopt.sendKeys("BOMBAY");
		
	
		Thread.sleep(3000);
		
		
		List<WebElement> optionsToSelect = driver.findElements(By.xpath("//div[@class='autocompleter-scroll-cntr']"));
		int totalSize = optionsToSelect.size();
		
		//System.out.println("**************************" + totalSize );
		
		//selecting the sag in list i.e. shirdi 
		for(int i = 0 ;i< optionsToSelect.size();i++)
		{
			System.out.println(optionsToSelect.get(i).getText());
			
			if(optionsToSelect.get(i).getText().contains("SAG"))
			{
				optionsToSelect.get(i).click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//*[text()='To']/following::*[@class='c-input u-v-align-middle'][2]")).click();
		
		///selecting the Dynamic date format for slecting calendar date  
		String  selectDat = "04/03/2021";
		
		Date d = new Date(selectDat);
		
		SimpleDateFormat dt = new SimpleDateFormat("MMMM/dd/yyyy");
		
		String dtae = dt.format(d);
		
		System.out.println(dtae);
		
		String[] split = dtae.split("/");
		System.out.println(split[0] + " " + split[1]);
		
		String month  = split[0] + " " + split[2];
		System.out.println("***********************"+month);
		Thread.sleep(6000);
		while(true) {
			try{
	
				Thread.sleep(2000);
				
				//selecting the date in calendar april 03 and 12 april
			WebElement tbd = driver.findElement(By.xpath("//*[contains(text(),'"+month+"')]"));
			System.out.println("sagdhgsadhgads" +tbd);
			tbd.isDisplayed();
			driver.findElement(By.xpath("//*[text()='April 2021']/following::*[@data-date='03042021']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[text()='April 2021']/following::*[@data-date='12042021'][2]")).click();
			break;
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				////clciking forward moving till april calendar
				driver.findElement(By.xpath("//*[@class='ixi-icon-arrow rd-next']")).click();
				Thread.sleep(2000);
			}
			}
		
	
		    driver.findElement(By.xpath("//*[text()='Search']")).click();
		    
		   Thread.sleep(3000);
		    
		 //  driver.findElement(By.xpath("//*[text()='Book']")).click();
		   WebElement element = driver.findElement(By.xpath("//*[text()='Book']"));
		   JavascriptExecutor executor = (JavascriptExecutor)driver;
		   executor.executeScript("arguments[0].click();", element);
		   Thread.sleep(4000);
		   
		   driver.close();
	}    

}
