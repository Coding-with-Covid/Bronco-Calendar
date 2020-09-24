package com.example.springboot;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.math3.primes.Primes;
import com.google.gson.Gson;

@RestController
public class WebController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!!!";
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

	@RequestMapping(value = "/testCommonMath", method = RequestMethod.GET)
        public String testCommonMath() {
		boolean x = Primes.isPrime(45);
    		if(x) {
    			return "45 is a prime number";
    		}
    		else
    			return "45 is not a prime number";
	}

	@RequestMapping(value = "/testGson", method = RequestMethod.GET)
    	public String testGson() {

       		Gson gson = new Gson();

        	String[] arr = {"hello", "world", "!"};
        	String test = gson.toJson(arr);

        	return test;
    	}

	@RequestMapping(value = "/testCommonsIO", method = RequestMethod.GET)
	public String testCommonsIO() {
		String filePath = "\"C:\\\\Users\\\\Ryan\\\\file.txt\"";
		String extension = FilenameUtils.getExtension(filePath);
		return extension;
	}
}
