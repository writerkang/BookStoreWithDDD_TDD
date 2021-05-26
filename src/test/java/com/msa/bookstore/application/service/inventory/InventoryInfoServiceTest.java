package com.msa.bookstore.application.service.inventory;

import com.msa.bookstore.domain.inventory.InventoryRepository;
import com.msa.bookstore.infra.repository.InventoryTestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class InventoryInfoServiceTest {
    private InventoryRepository repository;
    private InventoryService service;

    @Test
    void shouldIncreaseInventory() {
        repository = new InventoryTestRepository();
        service = new InventoryService(repository);
        var bookInfoId = 1L;
        var quantity = 3L;

        service.register(bookInfoId);
        service.increase(bookInfoId, quantity);
        var result = repository.findByBookInfoId(bookInfoId);

        Assertions.assertEquals(3, result.quantity());
    }

    @Test
    void shouldIncreaseEachInventory() {
        repository = new InventoryTestRepository();
        service = new InventoryService(repository);
        var bookInfoId = 1L;
        var quantity = 12L;
        var anotherBookInfoId = 100L;
        var anotherQuantity = 15L;

        service.register(bookInfoId);
        service.register(anotherBookInfoId);
        service.increase(bookInfoId, quantity);
        service.increase(anotherBookInfoId, anotherQuantity);
        var result = repository.findByBookInfoId(bookInfoId);
        var anotherResult = repository.findByBookInfoId(anotherBookInfoId);

        Assertions.assertEquals(quantity, result.quantity());
        Assertions.assertEquals(anotherQuantity, anotherResult.quantity());
    }

    @Test
    void shouldThrowExceptionWhenInvalidQuantity() {
        repository = new InventoryTestRepository();
        service = new InventoryService(repository);
        var bookInfoId = 1L;
        var quantity = 0L;
        var anotherBookInfoId = 100L;
        var anotherQuantity = -100L;

        service.register(bookInfoId);
        service.register(anotherBookInfoId);
        Executable result = () -> service.increase(bookInfoId, quantity);
        Executable anotherResult = () -> service.increase(anotherBookInfoId, anotherQuantity);

        Assertions.assertThrows(IllegalArgumentException.class, result);
        Assertions.assertThrows(IllegalArgumentException.class, anotherResult);
    }
}
