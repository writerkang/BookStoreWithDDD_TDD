package com.msa.bookstore.application.service.IntegrationTest;

import com.msa.bookstore.application.service.EventDispatcher;
import com.msa.bookstore.application.service.bookinfo.BookInfoRegisterService;
import com.msa.bookstore.application.service.bookinfo.RegisterBookInfoCommand;
import com.msa.bookstore.domain.bookinfo.BookInfoRepository;
import com.msa.bookstore.domain.inventory.InventoryRepository;
import com.msa.bookstore.infra.repository.BookInfoTestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest
class BookInfoInventoryIntegrationTest {
    private BookInfoRepository bookInfoRepository;
    private BookInfoRegisterService bookInfoRegisterService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    void shouldRegisterInventoryWhenBookInfoRegistered() throws InterruptedException {
        bookInfoRepository = new BookInfoTestRepository();
        bookInfoRegisterService = new BookInfoRegisterService(bookInfoRepository, new EventDispatcher(
            eventPublisher));
        RegisterBookInfoCommand command = new RegisterBookInfoCommand("A little price", "Jull Jebeker", 92441L);

        bookInfoRegisterService.registerBookInfo(command);
        Thread.sleep(1000);

        var foundBookInfo = bookInfoRepository.findByIsbn(92441L);
        var result = inventoryRepository.findByBookInfoId(foundBookInfo.id());
        Assertions.assertEquals(0, result.quantity());
    }
}
