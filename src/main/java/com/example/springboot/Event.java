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
    public String[] majors;

    public String month;
    public String date;
    public String time;
    public String day;
    public String img;

    public Event() {
    }

    public Event(String title, String dateTime, String hostedBy, String location, String desc, String[] majors, String day, String month, String date, String time, String img) {
        this.title = title;
        this.dateTime = dateTime;
        this.hostedBy = hostedBy;
        this.location = location;
        this.desc = desc;
        this.majors = majors;

        this.day = day;

        this.month = month;
        this.date = date;
        this.time = time;
        this.img = img;

    }

    @Override
    public String toString() {
        return String.format("Event[id=%s, title='%s', dateTime='%s', hostedBy='%s', location='%s', desc='%s']", id, title, dateTime, hostedBy, location, desc);
    }

    public String getTitle() {
        return title;
    }
}
