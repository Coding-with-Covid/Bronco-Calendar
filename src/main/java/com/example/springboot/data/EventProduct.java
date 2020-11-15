package com.example.springboot.data;

public class EventProduct {

    private String eventTitle;
    private String clubName;
    private String imageUrl;
    private String eventLocation;
    private String eventYear;
    private String eventMonth;
    private String eventDay;
    private String eventTime;
    private String eventDescription;
    
    public String getTitle() {
        return eventTitle;
    }

    public String getClubName() {
        return clubName;
    }

    public String getImageUrl() {
		return imageUrl;
	}

    public String getEventLocation() {
        return eventLocation;
    }

    public String getYear() {
        return eventYear;
    }

    public String getMonth() {
        return eventMonth;
    }

    public String getDay() {
        return eventDay;
    }

    public String getTime() {
        return eventTime;
    }

    public String getDescription() {
        return eventDescription;
    }

    public void setTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setYear(String eventYear) {
        this.eventYear = eventYear;
    }

    public void setMonth(String eventMonth) {
        this.eventMonth = eventMonth;
    }

    public void setDay(String eventDay) {
        this.eventDay = eventDay;
    }

    public void setTime(String eventTime) {
        this.eventTime = eventTime;
    }
    
    public void setDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
