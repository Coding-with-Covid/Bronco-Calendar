package com.example.springboot.calendar;

import com.example.springboot.Calendar;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarTest {

   //List of all possible day names
   private List<String> daysOfWeek = new ArrayList<>(
           Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday",
                   "Thursday", "Friday", "Saturday")
   );

   private Calendar c;

   @Before
   public void setup(){
      System.out.println("before testing...");
      c = new Calendar();
   }

   @After
   public void cleanup(){
      System.out.println("finishing and cleaning up the test");
   }

   @Test
   public void testCalendar() {
      //Assign day name
      String day = c.day("wednesday");

      //Check if assigned day name is valid
      boolean containsSearchStr = daysOfWeek.stream().anyMatch(day::equalsIgnoreCase);
      if (!containsSearchStr){
         throw new AssertionFailedError(
                 String.format("Invalid day: " + day + "\nExpecting: " + daysOfWeek)
         );
      }
   }

   //This one should fail
   @Test
   public void testCalendar2() {
      String day = c.day("asdfasdfasdf");

      boolean containsSearchStr = daysOfWeek.stream().anyMatch(day::equalsIgnoreCase);
      if (!containsSearchStr){
         throw new AssertionFailedError(
                 String.format("Invalid day: " + day + "\nExpecting: " + daysOfWeek)
         );
      }
   }
}
