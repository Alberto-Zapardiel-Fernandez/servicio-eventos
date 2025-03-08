package com.domain.usecases;

import com.domain.models.EventModel;
import com.domain.ports.out.EventRepositoryPort;

import java.util.List;
import java.util.Optional;

public class EventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public EventUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public List<EventModel> getAllEvents() {
        return eventRepositoryPort.findAll();
    }

    public Optional<EventModel> getEventById(Long id) {
        return eventRepositoryPort.findById(id);
    }

    public EventModel createEvent(EventModel event) {
        return eventRepositoryPort.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepositoryPort.deleteById(id);
    }
}