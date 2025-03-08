package com.application.ports.in;

import com.domain.models.EventModel;
import com.domain.usecases.EventUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Event service Impl
 *
 * @author Alberto Zapardiel Fernández
 */
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    /**
     * The event use case
     */
    private final EventUseCase eventUseCase;

    /**
     * Get all events
     *
     * @return the list of events
     */
    @Override
    public List<EventModel> getAllEvents() {
        return eventUseCase.getAllEvents();
    }

    /**
     * Get an event by id
     *
     * @param id the id
     * @return the event model
     */
    @Override
    public Optional<EventModel> getEventById(Long id) {
        return eventUseCase.getEventById(id);
    }

    /**
     * Create an event
     *
     * @param event the event
     * @return the event model
     */
    @Override
    public EventModel createEvent(EventModel event) {
        return eventUseCase.createEvent(event);
    }

    /**
     * Delete an event
     *
     * @param id the id
     */
    @Override
    public void deleteEvent(Long id) {
        eventUseCase.deleteEvent(id);
    }
}