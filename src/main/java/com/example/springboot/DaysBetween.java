package com.example.springboot;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class DaysBetween {
	public long CountDaysBetween(LocalDate currentDate, LocalDate eventDate){
		long daysBetween = ChronoUnit.DAYS.between(currentDate, eventDate);
		return daysBetween;
	}
}
