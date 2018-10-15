package com.group8.ciu196.beaconproject;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rasmu on 2018-10-06.
 */

public class BookManagerSingelton implements BookManager{

    private static final String TAG = "BookManagerSingelton";

    private static BookManagerSingelton bookManagerInstance = null;
    private static ArrayList<Book> books = new ArrayList<Book>();

    public static BookManagerSingelton getInstance(){
        if(bookManagerInstance == null){
            bookManagerInstance = new BookManagerSingelton();
        }

        return  bookManagerInstance;
    }


    @Override
    public int count() {
        return books.size();
    }

    @Override
    public Book getBook(int index) {
        return books.get(index);
    }

    @Override
    public Book createBook(String author, String title, int availability , String isbn, String shelf, String imageStr) {
        Book book = new Book(author, title, availability, isbn, shelf, imageStr);
        books.add(book);
        Log.i(TAG, "Book added");
        return book;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public void moveBook(int from, int to) {
        Book temp = books.get(from);
        books.set(from,books.get(to));
        books.set(to,temp);

    }


    @Override
    public void saveChanges(SharedPreferences sharedPreferences) {
        String title = sharedPreferences.getString("TITLE", null);
        String author = sharedPreferences.getString("AUTHOR", null);
        String shelf = sharedPreferences.getString("SHELF", null);
        int availability = sharedPreferences.getInt("AVAILABILITY", -1);
        String isbn = sharedPreferences.getString("ISBN", null);
        String imageStr = sharedPreferences.getString("IMAGESTR", null);


        int position = sharedPreferences.getInt("POS", -1);

        if(0 > position) {

            createBook(author, title, availability, isbn, shelf, imageStr);
        }else{
            Book bookUpdate = new Book(author, title, availability, isbn, shelf, imageStr);

            books.set(position, bookUpdate);
        }

    }
}
