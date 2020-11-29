package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EventService {
   private EventRepository eventRepository;

   @Autowired
   public void EventRepository(EventRepository eventRepository) {
      this.eventRepository = eventRepository;
   }

   public Iterable<Event> getAllEvents() {
      LocalDateTime dateTime = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
      return eventRepository.findByDateTimeGreaterThanOrderByDateTimeAsc(dateTime.format(formatter));
   }
}
