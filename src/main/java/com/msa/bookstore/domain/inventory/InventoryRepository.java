package com.msa.bookstore.domain.inventory;

public interface InventoryRepository {
    InventoryInfo findByBookInfoId(Long bookInfoId);
    Long nextId();
    void save(InventoryInfo inventoryInfo);
}
