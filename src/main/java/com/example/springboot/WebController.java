package com.example.springboot;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@Requestmapping(value = "/testJosh", metod = RequestMethod.GET)
	public String testJosh() {
		return "This is Josh's test for HTTP get";
	}
}
