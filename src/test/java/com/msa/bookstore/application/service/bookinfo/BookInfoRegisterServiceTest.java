package com.msa.bookstore.application.service.bookinfo;

import com.msa.bookstore.application.service.EventDispatcher;
import com.msa.bookstore.domain.bookinfo.BookInfoRepository;
import com.msa.bookstore.infra.repository.BookInfoTestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest
class BookInfoRegisterServiceTest {

    private BookInfoRepository repository;
    private BookInfoRegisterService service;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    RegisterBookInfoCommand command = new RegisterBookInfoCommand("A little price", "Jull Jebeker", 9875281921L);
    RegisterBookInfoCommand another = new RegisterBookInfoCommand("Rabbit and Turtle", "Kol Shouder", 39402243L);
    RegisterBookInfoCommand nullTitle = new RegisterBookInfoCommand(null, "Jull Jebeker", 9875281921L);
    RegisterBookInfoCommand blankTitle = new RegisterBookInfoCommand("        ", "Jull Jebeker", 9875281921L);
    RegisterBookInfoCommand nullAuthor = new RegisterBookInfoCommand("A little price", null, 9875281921L);
    RegisterBookInfoCommand blankAuthor = new RegisterBookInfoCommand("A little price", "       ", 9875281921L);
    RegisterBookInfoCommand zeroIsbn = new RegisterBookInfoCommand("A little price", "Jull Jebeker", 0L);
    RegisterBookInfoCommand negativeIsbn = new RegisterBookInfoCommand("A little price", "Jull Jebeker", -1L);

    @Test
    void shouldRegisterOneBookInfo() {
        repository = new BookInfoTestRepository();
        service = new BookInfoRegisterService(repository, new EventDispatcher(eventPublisher));

        service.registerBookInfo(command);
        var result = repository.count();

        Assertions.assertEquals(1, result);
    }

    @Test
    void shouldRegisterTwoBooksInfo() {
        repository = new BookInfoTestRepository();
        service = new BookInfoRegisterService(repository, new EventDispatcher(eventPublisher));

        service.registerBookInfo(command);
        service.registerBookInfo(another);
        var result = repository.count();

        Assertions.assertEquals(2, result);
    }

    @Test
    void shouldIgnoreWhenBookInfoAlreadyRegistered() {
        repository = new BookInfoTestRepository();
        service = new BookInfoRegisterService(repository, new EventDispatcher(eventPublisher));

        service.registerBookInfo(command);
        service.registerBookInfo(command);
        var result = repository.count();

        Assertions.assertEquals(1, result);
    }

    @Test
    void shouldPreventInvalidTitle() {
        repository = new BookInfoTestRepository();
        service = new BookInfoRegisterService(repository, new EventDispatcher(eventPublisher));

        Executable nullTitleResult = () -> service.registerBookInfo(nullTitle);
        Executable blankTitleResult = () -> service.registerBookInfo(blankTitle);

        Assertions.assertThrows(IllegalArgumentException.class, nullTitleResult);
        Assertions.assertThrows(IllegalArgumentException.class, blankTitleResult);
    }

    @Test
    void shouldPreventInvalidAuthor() {
        repository = new BookInfoTestRepository();
        service = new BookInfoRegisterService(repository, new EventDispatcher(eventPublisher));

        Executable nullAuthorResult = () -> service.registerBookInfo(nullAuthor);
        Executable blankAuthorResult = () -> service.registerBookInfo(blankAuthor);

        Assertions.assertThrows(IllegalArgumentException.class, nullAuthorResult);
        Assertions.assertThrows(IllegalArgumentException.class, blankAuthorResult);
    }

    @Test
    void shouldPreventInvalidIsbn() {
        repository = new BookInfoTestRepository();
        service = new BookInfoRegisterService(repository, new EventDispatcher(eventPublisher));

        Executable zeroIsbnResult = () -> service.registerBookInfo(zeroIsbn);
        Executable negativeIsbnResult = () -> service.registerBookInfo(negativeIsbn);

        Assertions.assertThrows(IllegalArgumentException.class, zeroIsbnResult);
        Assertions.assertThrows(IllegalArgumentException.class, negativeIsbnResult);
    }
}
