package com.example.springboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private EventRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

	@Override
	public void run(String... args) throws Exception {
		// Sample code for repo
		repo.deleteAll();

		// Sample list of associated majors
		String[] majorsList = {"Computer Science", "Computer Engineering"};
		// Add events
		repo.save(new Event("New Club Workshop", "2020-10-08T10:00:00", "Office of Student Life and Cultural Centers", "Zoom", "Club & Organization registration information for students seeking to establish new clubs on campus. No RSVP required. If you arrive late you may not be admitted. Zoom link provided here on the date of the programming.", majorsList));
		repo.save(new Event("Issa's Eco-Corner", "2020-10-08T11:00:00", "Womxn's Resource Center", "Zoom", "top by and chat with Issa about how to live more \"zero waste\", learn about the environment, DIY fruit fridge masks and more! During Issa's Eco-Corner we will be covering a variety of environmental-related topics, activities, and themes. This week's topic will be \"zero waste kits\" on every budget. Issa will be on Zoom ready to answer any questions you have in order to switch over to more eco-friendly products as well as having a free zero waste kit guide PDF.", majorsList));
		repo.save(new Event("Meet the Latinx Librarians & Staff", "2020-10-08T11:30:00", "University Library", "Online", "Did you know that the CPP Library has Latinx/Hispanic Librarians & Staff?  If you have ever asked this question or wondered how librarians landed their jobs, you're not alone. Get to know your librarians, who they are, how they can help, but most importantly their background and how their journey to the CPP Library! This community dialogue will feature the wonderful Latinx/Hispanic Librarians and attendees will learn more about who they are as people as well as how they support students.", majorsList));
	}
}
