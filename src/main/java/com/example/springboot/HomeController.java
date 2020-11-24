package com.example.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private EventService eventService;

    @Autowired
    public HomeController(EventService eventService) {
        this.eventService = eventService;
    }
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testHome1() { return "home.html"; }
	
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String testHome() { return "home.html"; }

    @GetMapping({"/events"})
    public String event(Model model) {
        List<Event> events = (List<Event>) eventService.getAllEvents();
        model.addAttribute("event", events);
        return "events";
    }

    @GetMapping({"/eventsLoggedOut"})
    public String eventLoggedOut(Model model) {
        List<Event> events = (List<Event>) eventService.getAllEvents();
        model.addAttribute("event", events);
        return "eventsLoggedOut";
    }



    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String testCalendar() {
        return "calendar.html";
    }
    
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String testWelcome() { return "welcome.html"; }
}
