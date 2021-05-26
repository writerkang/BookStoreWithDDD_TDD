package com.msa.bookstore.domain;

import java.time.LocalDateTime;

public abstract class Event {
    protected LocalDateTime createdAt = LocalDateTime.now();
}
