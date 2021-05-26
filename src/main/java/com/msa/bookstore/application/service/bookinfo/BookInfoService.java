package com.msa.bookstore.application.service.bookinfo;

import com.msa.bookstore.domain.bookinfo.BookInfoRepository;
import java.util.Objects;
import javax.transaction.Transactional;

public class BookInfoService {

    private final BookInfoRepository repository;

    public BookInfoService(BookInfoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void registerBookInfo(RegisterBookInfoCommand command) {
        Objects.requireNonNull(command);
        if (command.title() == null || command.title().isBlank()) {
            throw new IllegalArgumentException();
        }
        if (command.author() == null || command.author().isBlank()) {
            throw new IllegalArgumentException();
        }
        if (command.isbn() < 1) {
            throw new IllegalArgumentException();
        }

        var foundBookInfo = repository.findByIsbn(command.isbn());
        if (foundBookInfo != null) {
            return;
        }

        var newBookInfo = new BookInfo(command.title().strip(),
            command.author().strip(), command.isbn());

        this.repository.save(newBookInfo);
    }
}
