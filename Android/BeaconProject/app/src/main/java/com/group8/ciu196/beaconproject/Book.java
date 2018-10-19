package com.group8.ciu196.beaconproject;


public class Book {
    private String author;
    private String name;
    private int availability;
    private String isbn;
    private String shelf;
    private String imageStr;
    private String origin;
    private String publication;
    private String category;



    public Book(String id, String name, String author, int availability , String origin, String shelf, String publication, String category, String imageStr) {
        this.author = author;
        this.name = name;
        this.availability = availability;
        this.isbn = id;
        this.origin = origin;
        this.shelf = shelf;
        this.publication = publication;
        this.category = category;
        this.imageStr = imageStr;
    }

    public Book() {

    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return name;
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
        this.name = title;
    }

    public void setAvailability(int availability ) {
        this.availability  = availability ;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setShelf(String course) {
        this.shelf = shelf;
    }

    public String getImageStr(){
        return imageStr;
    }

    public void setImageStr(String imageStr){
        this.imageStr = imageStr;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrigin() {
        return origin;
    }

    public String getPublication() {
        return publication;
    }

    public String getCategory() {
        return category;
    }
}
