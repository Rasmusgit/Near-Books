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
    public Book createBook(String id, String name, String author, int availability , String origin, String shelf, String publication, String category, String imageStr) {
        Book book = new Book(id, name, author, availability, origin, shelf, publication, category, imageStr);
        books.add(book);
        Log.i(TAG, "Book added");
        return book;
    }


    public boolean add(Book book) {

        return  books.add(book);
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

    public ArrayList<Book> getBooksByCategory(String category){


        ArrayList<Book> categoryBooks = new ArrayList<Book>();

        for(int i = 0; i < books.size(); i++){
            Log.i(TAG,"category: " + category + " the book: " + books.get(i).getCategory());
            if(books.get(i).getCategory().equals(category)){
                categoryBooks.add(books.get(i));
            }
        }

        return categoryBooks;

    }

    @Override
    public void saveChanges(SharedPreferences sharedPreferences) {
        String name = sharedPreferences.getString("TITLE", null);
        String author = sharedPreferences.getString("AUTHOR", null);
        String shelf = sharedPreferences.getString("SHELF", null);
        int availability = sharedPreferences.getInt("AVAILABILITY", -1);
        String id = sharedPreferences.getString("ISBN", null);
        String imageStr = sharedPreferences.getString("IMAGESTR", null);
        String origin = sharedPreferences.getString("ORIGIN", null);
        String publication = sharedPreferences.getString("PUBLICATION", null);
        String category = sharedPreferences.getString("CATEGORY", null);

        int position = sharedPreferences.getInt("POS", -1);

        if(0 > position) {
            createBook(id, name, author, availability, origin, shelf, publication, category, imageStr);
        }else{
            Book bookUpdate = new Book(id, name, author, availability, origin, shelf, publication, category, imageStr);

            books.set(position, bookUpdate);
        }

    }
}
