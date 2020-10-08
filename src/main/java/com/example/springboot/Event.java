package com.example.springboot;

import org.springframework.data.annotation.Id;

public class Event {

    @Id
    public String id;

    public String title;
    public String dateTime;
    public String hostedBy;
    public String location;
    public String desc;

    public Event() {
    }

    public Event(String title, String dateTime, String hostedBy, String location, String desc) {
        this.title = title;
        this.dateTime = dateTime;
        this.hostedBy = hostedBy;
        this.location = location;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.format("Event[id=%s, title='%s', dateTime='%s', hostedBy='%s', location='%s', desc='%s']", id, title, dateTime, hostedBy, location, desc);
    }
}
