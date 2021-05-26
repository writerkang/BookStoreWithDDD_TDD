package com.msa.bookstore.infra.repository;

import com.msa.bookstore.application.service.bookinfo.BookInfo;
import com.msa.bookstore.domain.bookinfo.BookInfoRepository;
import java.util.HashMap;
import java.util.Map;

public class BookInfoTestRepository implements BookInfoRepository {

    private final Map<Long, BookInfo> bookInfoMap = new HashMap<>();
    private static Long SEQUENCE = 0L;

    @Override
    public int count() {
        return bookInfoMap.size();
    }

    @Override
    public void save(BookInfo bookInfo) {
        bookInfoMap.put(bookInfo.id(), bookInfo);
    }

    @Override
    public BookInfo findByIsbn(Long isbn) {

        return this.bookInfoMap.values()
            .stream()
            .filter(bookInfo -> bookInfo.isbn() == isbn)
            .findFirst()
            .orElse(null);
    }

    @Override
    public Long nextId() {
        return ++SEQUENCE;
    }
}
