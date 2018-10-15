package com.group8.ciu196.beaconproject;

import android.content.SharedPreferences;

import java.util.ArrayList;

public interface BookManager {
    public int count();
    public Book getBook(int index);
    public Book createBook(String author, String title, int availability , String isbn, String shelf);
    public ArrayList<Book> getAllBooks();
    public void removeBook(Book book);
    public void moveBook(int from, int to);
    public void saveChanges(SharedPreferences sharedPreferences);
}