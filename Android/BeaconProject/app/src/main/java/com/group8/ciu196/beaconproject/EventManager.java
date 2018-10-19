package com.group8.ciu196.beaconproject;

import java.util.ArrayList;

public class EventManager {


    private static EventManager eventManagerInstance = null;
    private static ArrayList<Event> events = new ArrayList<Event>();

    public static EventManager getInstance(){
        if(eventManagerInstance == null){
            eventManagerInstance = new EventManager();
        }

        return  eventManagerInstance;
    }


    public boolean addEvent(String title, String imageStr, String date, String startTime, String endTime, String detils){
        return events.add(new Event(title, imageStr, date, startTime, endTime, detils));
    }

    public ArrayList<Event> getAll(){
        return events;
    }




}
