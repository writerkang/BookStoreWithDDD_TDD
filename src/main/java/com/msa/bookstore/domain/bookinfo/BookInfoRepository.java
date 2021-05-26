package com.msa.bookstore.domain.bookinfo;

import com.msa.bookstore.application.service.bookinfo.BookInfo;

public interface BookInfoRepository {
    int count();
    void save(BookInfo bookInfo);
    BookInfo findByIsbn(Long isbn);
    Long nextId();
}
