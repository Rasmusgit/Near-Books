package com.group8.ciu196.beaconproject;


public class Book {
    private String author;
    private String title;
    private int availability;
    private String isbn;
    private String shelf;
    private String image;

    public Book(String author, String title, int availability , String isbn, String shelf, String imageStr) {
        this.author = author;
        this.title = title;
        this.availability = availability;
        this.isbn = isbn;
        this.shelf = shelf;
        this.image = imageStr;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailability() {
        return availability ;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getShelf() {
        return shelf;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAilability(int availability ) {
        this.availability  = availability ;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setShelf(String course) {
        this.shelf = shelf;
    }
}
