package com.group8.ciu196.beaconproject;

public class Event {

    private String title;
    private String image;
    private String date;
    private String time;
    private String details;


    public Event(String title, String imageStr, String date, String time, String details){
        this.title = title;
        this.image = imageStr;
        this.date = date;
        this.time = time;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
