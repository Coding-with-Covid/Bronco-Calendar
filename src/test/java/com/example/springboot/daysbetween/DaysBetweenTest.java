package com.example.springboot.daysbetween;

import com.example.springboot.DaysBetween;
import java.time.LocalDate;
import java.time.Month;
import org.junit.*;

public class DaysBetweenTest {
	@Before
	public void setup(){
		System.out.println("before testing...");
	}
	
	@After
	public void cleanup(){
		System.out.println("finishing and cleaning up the test");
	}
	
	@Test
	public void testDaysBetween1() {
		DaysBetween d = new DaysBetween();
		LocalDate currentDate = LocalDate.of(2020, Month.OCTOBER, 20);
		LocalDate eventDate = LocalDate.of(2020, Month.OCTOBER, 29);
		long days = d.CountDaysBetween(currentDate, eventDate);
		Assert.assertEquals(9, days);
		
	}
	
	//Not sure how to test for the following because LocalDate.now() changes by the day
	//Getting the number of days remaining before the event could be useful for reminders, etc.
	@Test 
	public void testDaysBetween2() {
		DaysBetween d = new DaysBetween();
		LocalDate currentDate = LocalDate.now();
		LocalDate eventDate = LocalDate.of(2020, Month.OCTOBER, 20);
		long days = d.CountDaysBetween(currentDate, eventDate);
		
	}
}
