package com.msa.bookstore.application.service.inventory;

import com.msa.bookstore.domain.bookinfo.BookInfoRegisteredEvent;
import com.msa.bookstore.domain.inventory.InventoryInfo;
import com.msa.bookstore.domain.inventory.InventoryRepository;
import javax.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void register(Long bookInfoId) {
        var newInventory = InventoryInfo.create(
            repository.nextId(),
            bookInfoId,
            0L
        );

        repository.save(newInventory);
    }

    @Async
    @EventListener
    @Transactional
    public void register(BookInfoRegisteredEvent event) {
        var newInventory = InventoryInfo.create(
            repository.nextId(),
            event.bookInfoId(),
            0L
        );

        repository.save(newInventory);
    }

    @Transactional
    public void increase(Long bookInfoId, Long quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException();
        }

        var foundInventory = repository.findByBookInfoId(bookInfoId);
        if (foundInventory == null) {
            throw new IllegalArgumentException();
        }


        foundInventory.increase(quantity);
    }
}
