package com.example.springboot;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPPEvents {
    public ArrayList<Event> getCPPEvents() throws IOException {
        // Create arraylist of events
        ArrayList<Event> events = new ArrayList<Event>();

        // Connect to page and get doc
        String url = "https://25livepub.collegenet.com/calendars/cpp-master-calendar?media=print";
        Connection conn = Jsoup.connect(url);
        Document doc = conn.get();

        // twRyoPhotoEventsItemHeader contains each event (contains date and location)
        Elements dateAndLoc = doc.getElementsByClass("twRyoPhotoEventsItemHeader");

        // twContentCell contains info on title and desc
        Elements titleAndDesc = doc.getElementsByClass("twContentCell");

        // Only add events if the number of titles/desc and dates/loc match
        if (dateAndLoc.size() == titleAndDesc.size()) {

            // Do this for every event that we have
            for (int i = 0; i < dateAndLoc.size(); i++) {
                Element myDateAndLoc = dateAndLoc.get(i);
                Element myTitleAndDesc = titleAndDesc.get(i);

                String dateTime = (myDateAndLoc.childNode(0)).childNode(0).toString();

                String day = getDay(dateTime);

                String[] dateTimeArray = dateTimeAsArray(dateTime);
                String month = dateTimeArray[0];
                String date = dateTimeArray[1];
                String time = dateTimeArray[2];
                dateTime = formatDate(dateTime);

                String title = (((myTitleAndDesc.childNode(0)).childNode(0)).childNode(0)).childNode(0).toString();
                title = title.replace("&amp;", "&");

                // Some events don't have a location
                String loc = "";
                if (myDateAndLoc.childNode(1).childNodeSize() != 0) {
                    loc = ((myDateAndLoc.childNode(1)).childNode(0)).childNode(0).toString();
                }

                // Some events don't have a description, leave those empty
                String desc = "";
                // Events with descriptions have 3 child nodes
                if (myTitleAndDesc.childNodeSize() == 3) {
                    if (myTitleAndDesc.childNode(1).childNodeSize() != 1) {
                        desc = ((myTitleAndDesc.childNode(1)).childNode(1)).toString();
                        desc = cleanString(desc);
                    }
                    else {
                        desc = ((myTitleAndDesc.childNode(1)).childNode(0)).childNode(0).toString();
                        desc = cleanString(desc);
                    }
                }

                // Enter associated majors later
                String[] majors = {};

                // Add event to list of events
                events.add(new Event(title, dateTime, "", loc, desc, majors, day, month, date, time, "https://media.licdn.com/dms/image/C560BAQH8mLo7H_WXuQ/company-logo_200_200/0?e=2159024400&v=beta&t=XzWwTpZ1F1HZ_OOj_xKkBDX9pcDvsdqOkRFU3E_C7J8"));
            }
        }
        return events;
    }

    private String getDay(String text) {
        if (text.contains("Sunday")) return "January";
        else if (text.contains("Monday")) return "Monday";
        else if (text.contains("Tuesday")) return "Tuesday";
        else if (text.contains("Wednesday")) return "Wednesday";
        else if (text.contains("Thursday")) return "Thursday";
        else if (text.contains("Friday")) return "Friday";
        else if (text.contains("Saturday")) return "Saturday";
        else return "";
    }

    private String[] dateTimeAsArray(String text) {
        String[] dateTime = {"","",""};
        String[] tokens = text.split("\\|"); // Split string by using | as delimiter
        dateTime[0] = getMonth(tokens[0]);
        dateTime[1] = getDate(tokens[0]);
        dateTime[2] = getTime(tokens[1]);
        return dateTime;
    }

    private String getMonth(String text) {
        if (text.contains("January")) return "January";
        else if (text.contains("February")) return "February";
        else if (text.contains("March")) return "March";
        else if (text.contains("April")) return "April";
        else if (text.contains("May")) return "May";
        else if (text.contains("June")) return "June";
        else if (text.contains("July")) return "July";
        else if (text.contains("August")) return "August";
        else if (text.contains("September")) return "September";
        else if (text.contains("October")) return "October";
        else if (text.contains("November")) return "November";
        else if (text.contains("December")) return "December";
        else return "";
    }

    private String getDate(String text) {
        String[] tokens = text.split(",");
        String temp = tokens[1];
        int date = Integer.parseInt(temp.replaceAll("[\\D]", ""));
        return String.format("%02d", date);  // Pad with 0's for width 2
    }

    private String getTime(String text) {
        text = text.replace("&nbsp;", "");
        String[] tokens = text.split("–");
        String time = tokens[0];
        time.replaceAll("[\\D]", "");
        if (text.contains("am")) {
            if (time.contains("am")) return time;
            time += "am";
        }
        else {
            if (time.contains("pm")) return time;
            time += "pm";
        }
        return time;
    }

    private String formatDate(String text) {
        String[] tokens = text.split(",|\\|"); // Split string by using , and | as delimiters
        String month = parseMonth(tokens[1]);
        String date = parseDate(tokens[1]);
        String year = parseYear(tokens[2]);
        String hour = parseHour(tokens[3]);
        String minutes = parseMinutes(tokens[3]);
        return year + "-" + month + "-" + date + "T" + hour + ":" + minutes + ":00";
    }

    private String parseMonth(String text) {
        if (text.contains("January")) return "01";
        else if (text.contains("February")) return "02";
        else if (text.contains("March")) return "03";
        else if (text.contains("April")) return "04";
        else if (text.contains("May")) return "05";
        else if (text.contains("June")) return "06";
        else if (text.contains("July")) return "07";
        else if (text.contains("August")) return "08";
        else if (text.contains("September")) return "09";
        else if (text.contains("October")) return "10";
        else if (text.contains("November")) return "11";
        else if (text.contains("December")) return "12";
        else return "ERROR";
    }

    private String parseDate(String text) {
        int date = Integer.parseInt(text.replaceAll("[\\D]", ""));
        return String.format("%02d", date);  // Pad with 0's for width 2
    }

    private String parseYear(String text) {
        if (text.contains("2020")) {
            return "2020";
        }
        else if (text.contains("2021")) {
            return "2021";
        }
        else {
            return "2020";
        }
//        String trimmed = text.trim();
//        int year = Integer.parseInt(trimmed);
//        return Integer.toString(year);
    }

    private String parseHour(String text) {
        String cleaned = text.replace("&nbsp;", "");  // Get rid of &nbsp;

        // Remove ending time from string
        String startTime = StringUtils.substringBefore(cleaned, "-");

        // First number we find is our starting time
        Matcher matcher = Pattern.compile("\\d+").matcher(cleaned);
        matcher.find();
        int hour = Integer.valueOf(matcher.group());

        if (startTime.contains("pm")) hour += 12;  // Add 12 if it's pm for 24hr time

        return String.format("%02d", hour);
    }

    private String parseMinutes(String text) {
        String cleaned = text.replace("&nbsp;", "");  // Get rid of &nbsp;
        String[] tokens = cleaned.split("\u2013");  // Everything before '–' is the start time

        if (tokens[0].contains(":")) {  // Look at the minutes for starting time only
            String minutes = tokens[0].substring(tokens[0].lastIndexOf(":") + 1);
            minutes = minutes.replace(" ", "");  // Get rid of space
            minutes = minutes.replaceAll("\\D+","");  // Don't want am/pm text
            return minutes;
        }
        else {  // If there is no ':', starts at 0 minutes
            return "00";
        }
    }

    private String cleanString(String desc) {
        String s = desc;
        // Use regex to get rid of tags
        s = s.replaceAll("<[^>]*>", "");
        s = s.replace("&nbsp;", "");
        return s;
    }
}
