package com.msa.bookstore.application.service.bookinfo;

import java.awt.print.Book;
import java.util.Objects;

public class BookInfo {
    private final Long id;
    private final String title;
    private final String author;
    private final Long isbn;

    private BookInfo(Long id, String title, String author, Long isbn) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(author);
        if (id < 1) {
            throw new IllegalArgumentException();
        }
        if (title.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (author.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (isbn < 1) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public static BookInfo create(Long id, String title, String author, Long isbn) {
        return new BookInfo(id, title, author, isbn);
    }

    public Long isbn() {
        return this.isbn;
    }

    public Long id() {
        return id;
    }
}
