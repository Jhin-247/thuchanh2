package com.example.th2.model;

import java.io.Serializable;

public class Book implements Serializable {
    public static final String BOOK_ID = "id";
    public static final String BOOK_NAME = "name";
    public static final String BOOK_AUTHOR = "singer";
    public static final String BOOK_RESUME = "resume";
    public static final String BOOK_PUBLISHER = "publisher";
    public static final String BOOK_RATING = "rating";


    private int id;
    private String name;
    private String author;
    private String resume;
    private String publisher;
    private float rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
