package com.group8.ciu196.beaconproject;

import java.util.ArrayList;

public class EventManager {


    private static EventManager eventManagerInstance = null;
    private static ArrayList<Event> events = new ArrayList<Event>();

    public static EventManager getInstance(){
        if(eventManagerInstance == null){
            eventManagerInstance = new EventManager();

            events.add(new Event("MUSIKPRODUKTION FÖR DIG 13-25 ÅR", "event0", "20181018", "1600", "1800", "Tio workshops för dig som vill skapa egen musik! Kanske blir det din nya hobby eller din framtida karriär? TV-spelsmusikkompositören Jacob Lincke lär dig allt om musikproduktion, olika genrer och hur man skapar passande musik och stämning till något visuellt som till exempel ett spel."));
            events.add(new Event("DEN NYA STADEN", "event1", "20181022", "1230", "1245", "Är du nyfiken på hur centrala Göteborg kommer att utvecklas? I en serie programpunkter på Stadsbiblioteket 300m2 i Brunnsparken berättar projektledare och arkitekter från Stadsbyggnadskontoret om några spännande och aktuella stadsutvecklingsprojekt."));
            events.add(new Event("START FÖR FÖRÄLDRAUTBILDNING: DYSLEXI/LÄS- OCH SKRIVSVÅRIGHETER", "event2", "20181016", "1800", "2000", "En kurs för dig som har barn med dyslexi eller läs- och skrivsvårigheter. Om hur du kan hjälpa och stödja ditt barn samt utveckla samarbetet med skolan. Ledare är specialpedagog och tal- och språkpedagog Helena Jacobsson som själv är förälder till barn med dyslexi."));

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
