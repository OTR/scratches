package com.github.otr.practical_tdd_for_java_programmers.domain;

/**
 *
 */
public class Book {

    private final String isbnNumber;
    private final String author;
    private final String title;

    public Book(String isbnNumber, String author, String title) {
        this.isbnNumber = isbnNumber;
        this.author = author;
        this.title = title;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

}
