package com.example.springboot;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;


@RestController
public class WebController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value = "/testRyan", method = RequestMethod.GET)
	public String testRyan() {
		return "This is Ryan's test for HTTP Get";
	}

	@RequestMapping(value = "/testJosh", method = RequestMethod.GET)
	public String testJosh() {
		return "This is Josh's test for HTTP GET";
	}
	
	@RequestMapping(value = "/testDuc", method = RequestMethod.GET)
	public String testDuc() {
        return "This is Duc's test for HTTP GET";
    }
	
	@RequestMapping(value = "/testSam", method = RequestMethod.GET)
	public String testSam() {
        return "This is Sam's test for HTTP GET";
	}
	
	//This is to test the jsoup library
	@RequestMapping(value = "/testJsoup", method = RequestMethod.GET)
	public String testJsoup() {
		
		Document doc;
		try {
			
			// need http protocol
			doc = Jsoup.connect("http://google.com").get();

			// get page title
			String title = doc.title();
			System.out.println("title : " + title);

			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links) {

				// get the value from href attribute
				System.out.println("\nlink : " + link.attr("href"));
				System.out.println("text : " + link.text());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

   	public static void main(String[] args) {
		//Add name of method here?
	}
}

