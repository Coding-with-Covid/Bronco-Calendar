package com.example.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String testHome() { return "home.html"; }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String testEvents() {
        return "events.html";
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String testCalendar() {
        return "calendar.html";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String testLogin() { return "login.html"; }

}