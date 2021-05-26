package com.msa.bookstore.infra.repository;

import com.msa.bookstore.application.service.bookinfo.BookInfo;
import com.msa.bookstore.domain.bookinfo.BookInfoRepository;
import java.util.HashMap;
import java.util.Map;

public class BookInfoTestRepository implements BookInfoRepository {

    private final Map<Long, BookInfo> bookInfoMap = new HashMap<>();

    @Override
    public int count() {
        return bookInfoMap.size();
    }

    @Override
    public void save(BookInfo bookInfo) {
        bookInfoMap.put(bookInfo.isbn(), bookInfo);
    }

    @Override
    public BookInfo findByIsbn(Long isbn) {
        return bookInfoMap.get(isbn);
    }
}
