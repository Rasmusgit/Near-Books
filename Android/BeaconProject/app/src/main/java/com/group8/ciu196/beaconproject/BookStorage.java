package com.group8.ciu196.beaconproject;

import java.util.ArrayList;

public class BookStorage {
    private static final String TAG = "BookStorage";

    private static BookStorage bookStorageInstance = null;
    private static ArrayList<Book> books = new ArrayList<Book>();

    String arc = "{\n" +
            "  \"0\": {\n" +
            "    \"name\": \"Women Design: Pioneers in architecture, industrial, graphic\",\n" +
            "    \"author\": \"Libby Sellers\",\n" +
            "    \"availability\": \"1\",\n" +
            "    \"origin\": \"Sellers, Libby Author.\",\n" +
            "    \"shelf\": \"-\",\n" +
            "    \"publication\": \"2018\"\n" +
            "  },\n" +
            "  \"1\": {\n" +
            "    \"name\": \"New architecture London\",\n" +
            "    \"author\": \"Agnese Sanvito, Richard Schulman\",\n" +
            "    \"availability\": \"2\",\n" +
            "    \"origin\": \"-\",\n" +
            "    \"shelf\": \"Ic\",\n" +
            "    \"publication\": \"2017\"\n" +
            "  },\n" +
            "  \"2\": {\n" +
            "    \"name\": \"Adjaye · Africa · Architecture\",\n" +
            "    \"author\": \"David Adjaye\",\n" +
            "    \"availability\": \"1\",\n" +
            "    \"origin\": \"Adjaye, David Author.\",\n" +
            "    \"shelf\": \"Ic.6\",\n" +
            "    \"publication\": \"2016\"\n" +
            "  },\n" +
            "  \"3\": {\n" +
            "    \"name\": \"Sketchup & Layout for architecture: the step by step workflow of Nick Sonder\",\n" +
            "    \"author\": \"Matt Donley, Nick Sonder\",\n" +
            "    \"availability\": \"2\",\n" +
            "    \"origin\": \"Donley, Matt\",\n" +
            "    \"shelf\": \"Ic\",\n" +
            "    \"publication\": \"2016\"\n" +
            "  },\n" +
            "  \"4\": {\n" +
            "    \"name\": \"Romanesque architecture\",\n" +
            "    \"author\": \"Eric Fernie\",\n" +
            "    \"availability\": \"10023\",\n" +
            "    \"origin\": \"Donley, Matt\",\n" +
            "    \"shelf\": \"Ic\",\n" +
            "    \"publication\": \"2014\"\n" +
            "  }\n" +
            "}";

    public static BookStorage getInstance(){
        if(bookStorageInstance == null){
            bookStorageInstance = new BookStorage();
        }
        return bookStorageInstance;
    }

    public String getArchitectureBooks(){
        return arc;
    }
}
