package com.msa.bookstore.domain.bookinfo;

public interface BookInfoRepository {
    int count();
    void save(BookInfo bookInfo);
    BookInfo findByIsbn(Long isbn);
    Long nextId();
}
