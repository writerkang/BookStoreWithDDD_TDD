package com.msa.bookstore.application.service.bookinfo;

import com.msa.bookstore.application.service.EventDispatcher;
import com.msa.bookstore.domain.bookinfo.BookInfo;
import com.msa.bookstore.domain.bookinfo.BookInfoRepository;
import java.util.Objects;
import javax.transaction.Transactional;

public class BookInfoRegisterService {

    private final BookInfoRepository repository;
    private final EventDispatcher eventDispatcher;

    public BookInfoRegisterService(BookInfoRepository repository,
        EventDispatcher eventDispatcher) {
        this.repository = repository;
        this.eventDispatcher = eventDispatcher;
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

        var newBookInfo = BookInfo.create(repository.nextId(), command.title().strip(),
            command.author().strip(), command.isbn());

        this.repository.save(newBookInfo);
        eventDispatcher.publish(newBookInfo.recordedEvents());
    }
}
