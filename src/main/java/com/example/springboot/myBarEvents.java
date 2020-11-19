package com.example.springboot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class myBarEvents {
	public ArrayList<Event> getMyBarEvents(){
		/* To get this to work, I downloaded the ChromeDriver version that matches the version of google chrome on my pc. 
		 * https://chromedriver.chromium.org/downloads
		 * https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver  
		 * I created the folder 'ChromeDriver' in my C drive and placed the chromedriver.exe in it.*/
		
		System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://mybar.cpp.edu/events");
		
		for(int x = 0; x <= 1; x++) {
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/button")).click();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		List<WebElement> linkNames = driver.findElements(By.tagName("a"));
		
		ArrayList<String> links = new ArrayList<String>();
		
		for(WebElement element : linkNames) {
			String link = element.getAttribute("href");
			if(link.startsWith("https://mybar.cpp.edu/event/6")) {
				links.add(link);
			}
		}
		driver.quit();
		
		ArrayList<Event> events = new ArrayList<Event>();
		
		//empty array
		String[] majorsList = {}; 
		
		for(String link : links) {
			WebDriver linkDriver = new ChromeDriver();
			linkDriver.get(link);
			
			
			String title = getString(linkDriver,"/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[2]/div[1]/div/div/span[1]/h1");
			
			String dateTime = getString(linkDriver,"/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[2]/div[2]/div[1]/div/div[2]/p[1]");
			dateTime = dateTime.substring(0, dateTime.length() - 3);
			
			String hostedBy = getString(linkDriver, "/html/body/div[2]/div/div/div/div/div/div/div[3]/div/a/div/div/div/span/div/div/h3");
															  
			String location = getString(linkDriver, "/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[2]/div[2]/div[2]/div/div[2]/p");
															    
			String description = getString(linkDriver, "/html/body/div[2]/div/div/div/div/div/div/div[2]/div[1]");
			description = (description.replace("Online Location Instructions", ""));
			description = (description.replace("Description", ""));
			
			
			Event event = new Event(title, dateTime, hostedBy, location, description, majorsList, "", "", "", "" );
			events.add(event);
			
			
			linkDriver.quit();
		}
		
		return events;
	}
	
	public String getString(WebDriver driver, String xpath) {
		for(int i=0; i<=2;i++){
			  try{
				 String string = driver.findElement(By.xpath(xpath)).getText();
				 return string;
			  }
			  catch(Exception e){
			     System.out.println(e.getMessage());
			  }
		}
		return null;
	}
		
}
