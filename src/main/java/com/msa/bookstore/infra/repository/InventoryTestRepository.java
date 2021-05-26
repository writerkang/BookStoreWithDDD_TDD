package com.msa.bookstore.infra.repository;

import com.msa.bookstore.domain.bookinfo.BookInfo;
import com.msa.bookstore.domain.inventory.InventoryInfo;
import com.msa.bookstore.domain.inventory.InventoryRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryTestRepository implements InventoryRepository {
    private final Map<Long, InventoryInfo> inventoryInfoMap = new HashMap<>();
    private static Long SEQUENCE = 0L;

    @Override
    public InventoryInfo findByBookInfoId(Long bookInfoId) {
        return this.inventoryInfoMap.values()
            .stream()
            .filter(inventoryInfo -> inventoryInfo.bookInfoId().equals(bookInfoId))
            .findFirst()
            .orElse(null);
    }

    @Override
    public Long nextId() {
        return ++SEQUENCE;
    }

    @Override
    public void save(InventoryInfo inventoryInfo) {
        inventoryInfoMap.put(inventoryInfo.id(), inventoryInfo);
    }
}
