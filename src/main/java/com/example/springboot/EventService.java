package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventService {
   private EventRepository eventRepository;

   @Autowired
   public void EventRepository(EventRepository eventRepository) {
      this.eventRepository = eventRepository;
   }

   public Iterable<Event> getAllEvents() {
      return eventRepository.findAll(Sort.by("dateTime"));
   }
}
