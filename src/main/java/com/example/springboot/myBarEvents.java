package com.example.springboot;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class myBarEvents {
	public ArrayList<Event> getMyBarEvents(){
		/* To get this to work, I downloaded the ChromeDriver version that matches the version of google chrome on my pc. 
		 * https://chromedriver.chromium.org/downloads
		 * https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver  
		 * I created the folder 'ChromeDriver' in my C drive and placed the chromedriver.exe in it.*/
		
		System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://mybar.cpp.edu/events");
		
		
		List<WebElement> linkNames = driver.findElements(By.tagName("a"));
		
		System.out.println("Number of Events on Current Page: " + linkNames.size());
		
		ArrayList<String> links = new ArrayList<String>();
		
		for(WebElement element : linkNames) {
			String link = element.getAttribute("href");
			if(link.startsWith("https://mybar.cpp.edu/event/6")) {
				links.add(link);
			}
		}
		driver.close();
		
		ArrayList<Event> events = new ArrayList<Event>();
		
		//empty array
		String[] majorsList = {}; 
		
		for(String link : links) {
			WebDriver linkDriver = new ChromeDriver();
			linkDriver.get(link);
			WebElement tElement = linkDriver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[2]/div[1]/div/div/span[1]/h1"));
			String title = tElement.getText();
			
			WebElement dTElement = linkDriver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[2]/div[2]/div[1]/div/div[2]/p[1]"));
			String dateTime = dTElement.getText();
			dateTime = dateTime.substring(0, dateTime.length() - 3);
			
			WebElement hElement = linkDriver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/a/div/div/div/span/div/div/h3"));
			String hostedBy = hElement.getText();
			
			WebElement lElement = linkDriver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[2]/div[2]/div[2]/div/div[2]/p"));
			String location = lElement.getText();
			
			WebElement dElement = linkDriver.findElement(By.className("DescriptionText"));
			String description = dElement.getText();
			
			Event event = new Event(title, dateTime, hostedBy, location, description, majorsList ); 
			events.add(event);
			
			linkDriver.close();
		}
		return events;
	}
}
