package com.msa.bookstore.domain.bookinfo;

import com.msa.bookstore.domain.Event;

public class BookInfoRegisteredEvent extends Event {
    private final Long bookInfoId;

    public BookInfoRegisteredEvent(Long bookInfoId) {
        super();
        this.bookInfoId = bookInfoId;
    }

    public Long bookInfoId() {
        return bookInfoId;
    }
}
