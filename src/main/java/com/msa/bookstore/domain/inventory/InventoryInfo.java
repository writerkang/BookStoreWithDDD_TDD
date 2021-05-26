package com.msa.bookstore.domain.inventory;

public class InventoryInfo {
    private Long id;
    private Long bookInfoId;
    private Long quantity;

    private InventoryInfo(Long id, Long bookInfoId, Long quantity) {
        this.id = id;
        this.bookInfoId = bookInfoId;
        this.quantity = quantity;
    }

    public static InventoryInfo create(Long id, Long bookInfoId, Long quantity) {
        return new InventoryInfo(id, bookInfoId, quantity);
    }

    public void increase(Long quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException();
        }

        this.quantity += quantity;
    }

    public Long id() {
        return id;
    }

    public Long quantity() {
        return quantity;
    }

    public Long bookInfoId() {
        return bookInfoId;
    }
}
