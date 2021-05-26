package com.msa.bookstore.application.service;

import com.msa.bookstore.domain.Event;
import java.util.List;
import org.springframework.context.ApplicationEventPublisher;

public class EventDispatcher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public EventDispatcher(
        ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(List<Event> events) {
        events.forEach(
            this.applicationEventPublisher::publishEvent);
    }
}
