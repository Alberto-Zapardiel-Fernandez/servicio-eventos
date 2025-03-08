package com.domain.ports.in;

import com.domain.models.EventModel;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<EventModel> getAllEvents();
    Optional<EventModel> getEventById(Long id);
    EventModel createEvent(EventModel event);
    void deleteEvent(Long id);
}
