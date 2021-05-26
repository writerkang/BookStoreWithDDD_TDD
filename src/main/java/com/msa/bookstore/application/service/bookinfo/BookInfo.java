package com.msa.bookstore.application.service.bookinfo;

public class BookInfo {
    private final String title;
    private final String author;
    private final Long isbn;

    public BookInfo(String title, String author, Long isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Long isbn() {
        return this.isbn;
    }
}
