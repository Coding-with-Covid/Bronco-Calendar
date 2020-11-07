package com.example.springboot;

import java.io.IOException;
import java.util.ArrayList;
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
		//repo.deleteAll();
		updateDatabase();
	}

	private void updateDatabase() throws IOException {
		CPPEvents CppEventHandler = new CPPEvents();
		ArrayList<Event> cppEvents = CppEventHandler.getCPPEvents();
		for (Event e : cppEvents) {
			if (!repo.existsEventByTitle(e.title)) {  // If this event isn't in database, add it
				repo.save(e);
			}
		}

//		myBarEvents myBarEventHandler = new myBarEvents();
//		ArrayList<Event> myBarEvents = myBarEventHandler.getMyBarEvents();
//		for (Event e : myBarEvents) {
//			if (!repo.existsEventByTitle(e.title)) {
//				repo.save(e);
//			}
//		}
	}
}
