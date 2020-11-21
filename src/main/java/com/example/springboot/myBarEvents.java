package com.example.springboot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class myBarEvents {
	public ArrayList<Event> getMyBarEvents(){
		/* To get this to work, I downloaded the ChromeDriver version that matches the version of google chrome on my pc. 
		 * https://chromedriver.chromium.org/downloads
		 * https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver  
		 * I created the folder 'ChromeDriver' in my C drive and placed the chromedriver.exe in it.*/
		
		System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://mybar.cpp.edu/events");
		
		for(int x = 0; x <= 1; x++) {
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/button")).click();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Loaded more events");
		
		List<WebElement> linkNames = driver.findElements(By.tagName("a"));
		
		ArrayList<String> links = new ArrayList<String>();
		
		for(WebElement element : linkNames) {
			String link = element.getAttribute("href");
			if(link.startsWith("https://mybar.cpp.edu/event/6")) {
				links.add(link);
			}
		}
		System.out.println("# of links:" + links.size());
		driver.quit();
		
		ArrayList<Event> events = new ArrayList<Event>();
		
		//empty array
		String[] majorsList = {}; 
		
		for(String link : links) {
			ChromeOptions linkOptions = new ChromeOptions();
			linkOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
			WebDriver linkDriver = new ChromeDriver(linkOptions);
			linkDriver.get(link);
			
			String title = getString(linkDriver,"/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[2]/div[1]/div/div/span[1]/h1");
			String dateTime = getString(linkDriver,"/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[2]/div[2]/div[1]/div/div[2]/p[1]");
			dateTime = dateTime.substring(0, dateTime.length() - 3);
			String month = getMonth(dateTime);
			String year = getYear(dateTime);
			String day = getDay(dateTime, month, year);
			String time = getTime(dateTime);
			String dateFormatted = formatDate(year, month, day, time);
			
			String hostedBy = getString(linkDriver, "/html/body/div[2]/div/div/div/div/div/div/div[3]/div/a/div/div/span/div/div/h3");
			String location = getString(linkDriver, "/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[2]/div[2]/div[2]/div/div[2]/p");
			String description = getString(linkDriver, "/html/body/div[2]/div/div/div/div/div/div/div[2]/div[1]");
			description = (description.replace("Online Location Instructions", ""));
			description = (description.replace("Description", ""));
			
			String imageURL = getURL(linkDriver, "/html/body/div[2]/div/div/div/div/div/div/div[1]/div/div/div[1]/div");
			
			Event event = new Event(title, dateFormatted, hostedBy, location, description, majorsList, month, day, time, imageURL); 
			events.add(event);			
			
			linkDriver.quit();
		}
		System.out.println("Done Scraping");
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
	
	public String getURL(WebDriver driver, String xpath) {
		for(int i=0; i<=2;i++){
			  try{
				 WebElement image = driver.findElement(By.xpath(xpath));
				 String imageURL = image.getCssValue("background-image");
				 imageURL = (imageURL.replace("url(\"", ""));
				 imageURL = (imageURL.replace("\")", ""));
				 return imageURL;
			  }
			  catch(Exception e){
			     System.out.println(e.getMessage());
			  }
		}
		return null;
	}
	
	public String getMonth(String str) {
		if(str.contains("November"))return "November";
		else if(str.contains("December"))return "December";
		else if(str.contains("January"))return "January";
		else if(str.contains("February"))return "February";
		else if(str.contains("March"))return "March";
		else if(str.contains("April"))return "April";
		else if(str.contains("May"))	return "May";
		else if(str.contains("June"))return "June";
		else if(str.contains("July"))return "July";
		else if(str.contains("August"))return "August";
		else if(str.contains("September"))return "September";
		else if(str.contains("October"))return "October";			
		return null;
	}
	
	public String getDay(String dateTime, String month, String year) {
		String day = dateTime.substring(dateTime.indexOf(month) + month.length() + 1, dateTime.indexOf(" " + year));
		day = day.substring(0, Math.min(day.length(), 2));
		return day;
	}
	public String getFormatted(String day){
		if(day.length() == 1) {
			String formatted = "0" + day;
			return formatted;
		}
		return day;
	}
	
	public String getTime(String dateTime) {
		String time = dateTime.substring(dateTime.indexOf("at") + 3, dateTime.length());
		time = (time.replace("PST", ""));
		return time;
	}
	
	
	public String formatDate(String year, String month, String day, String time) {
		String monthInt = getMonthInt(month);
		String dayF = getFormatted(day);
		String hour = getMilitaryHour(time);
		String minutes = time.substring(time.indexOf(":") + 1, time.length() - 4);
		String formatted = year + "-" + monthInt + "-" + day + "T" + hour + ":" + minutes + ":00";
				
		return formatted;
	}
	
	public String getMonthInt(String str) {
		if(str.contains("November"))return "11";
		else if(str.contains("December"))return "12";
		else if(str.contains("January"))return "01";
		else if(str.contains("February"))return "02";
		else if(str.contains("March"))return "03";
		else if(str.contains("April"))return "04";
		else if(str.contains("May"))	return "05";
		else if(str.contains("June"))return "06";
		else if(str.contains("July"))return "07";
		else if(str.contains("August"))return "08";
		else if(str.contains("September"))return "09";
		else if(str.contains("October"))return "10";			
		return null;
	}
	
	public String getYear(String dateTime) {
		if(dateTime.contains("2020"))return "2020";
		else if(dateTime.contains("2021"))return "2021";
		else if(dateTime.contains("2022"))return "2022";
		return null;
	}
	
	public static String getMilitaryHour(String time){
		String hour = time.substring(0, time.indexOf(":"));
		int hourInt = Integer.parseInt(hour);
		if(time.contains("PM") && hourInt != 12) {
			hourInt = hourInt + 12;
			String hourString = Integer.toString(hourInt);
			return hourString;
		}
		else if(time.contains("AM") && (hour.length() == 1)) return "0" + hour;
		
		return hour;
	}
	
}

