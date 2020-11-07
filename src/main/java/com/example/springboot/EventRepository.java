package com.example.springboot;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

    public Event findByTitle(String title);
    public boolean existsEventByTitle(String title);
}
