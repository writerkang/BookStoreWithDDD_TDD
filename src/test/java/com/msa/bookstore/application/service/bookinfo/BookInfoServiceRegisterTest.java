package com.msa.bookstore.application.service.bookinfo;

import com.msa.bookstore.domain.bookinfo.BookInfoRepository;
import com.msa.bookstore.infra.repository.BookInfoTestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class BookInfoServiceRegisterTest {

    private BookInfoRepository repository;
    private BookInfoService service;

    @Test
    void shouldRegisterOneBookInfo() {
        repository = new BookInfoTestRepository();
        service = new BookInfoService(repository);
        var command = new RegisterBookInfoCommand("A little price", "Jull Jebeker", 9875281921L);

        service.registerBookInfo(command);
        var result = repository.count();

        Assertions.assertEquals(1, result);
    }

    @Test
    void shouldRegisterTwoBooksInfo() {
        repository = new BookInfoTestRepository();
        service = new BookInfoService(repository);
        var command1 = new RegisterBookInfoCommand("A little price", "Jull Jebeker", 9875281921L);
        var command2 = new RegisterBookInfoCommand("Rabbit and Turtle", "Kol Shouder", 39402243L);

        service.registerBookInfo(command1);
        service.registerBookInfo(command2);
        var result = repository.count();

        Assertions.assertEquals(2, result);
    }

    @Test
    void shouldIgnoreWhenBookInfoAlreadyRegistered() {
        repository = new BookInfoTestRepository();
        service = new BookInfoService(repository);
        var command = new RegisterBookInfoCommand("A little price", "Jull Jebeker", 9875281921L);

        service.registerBookInfo(command);
        service.registerBookInfo(command);
        var result = repository.count();

        Assertions.assertEquals(1, result);
    }

    @Test
    void shouldPreventInvalidTitle() {
        repository = new BookInfoTestRepository();
        service = new BookInfoService(repository);
        var command1 = new RegisterBookInfoCommand(null, "Jull Jebeker", 9875281921L);
        var command2 = new RegisterBookInfoCommand("", "Jull Jebeker", 9875281921L);
        var command3 = new RegisterBookInfoCommand("     ", "Jull Jebeker", 9875281921L);

        Executable result1 = () -> service.registerBookInfo(command1);
        Executable result2 = () -> service.registerBookInfo(command2);
        Executable result3 = () -> service.registerBookInfo(command3);

        Assertions.assertThrows(IllegalArgumentException.class, result1);
        Assertions.assertThrows(IllegalArgumentException.class, result2);
        Assertions.assertThrows(IllegalArgumentException.class, result3);
    }

    @Test
    void shouldPreventInvalidAuthor() {
        repository = new BookInfoTestRepository();
        service = new BookInfoService(repository);
        var command1 = new RegisterBookInfoCommand("A little price", null, 9875281921L);
        var command2 = new RegisterBookInfoCommand("A little price", "", 9875281921L);
        var command3 = new RegisterBookInfoCommand("A little price", "       ", 9875281921L);

        Executable result1 = () -> service.registerBookInfo(command1);
        Executable result2 = () -> service.registerBookInfo(command2);
        Executable result3 = () -> service.registerBookInfo(command3);

        Assertions.assertThrows(IllegalArgumentException.class, result1);
        Assertions.assertThrows(IllegalArgumentException.class, result2);
        Assertions.assertThrows(IllegalArgumentException.class, result3);
    }

    @Test
    void shouldPreventInvalidIsbn() {
        repository = new BookInfoTestRepository();
        service = new BookInfoService(repository);
        var command1 = new RegisterBookInfoCommand("A little price", "Jull Jebeker", 0L);
        var command2 = new RegisterBookInfoCommand("A little price", "Jull Jebeker", -1L);

        Executable result1 = () -> service.registerBookInfo(command1);
        Executable result2 = () -> service.registerBookInfo(command2);

        Assertions.assertThrows(IllegalArgumentException.class, result1);
        Assertions.assertThrows(IllegalArgumentException.class, result2);
    }
}
