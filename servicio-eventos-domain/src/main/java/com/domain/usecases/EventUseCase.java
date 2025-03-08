package com.domain.usecases;

import com.domain.models.EventModel;
import com.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * Event Use Case
 *
 * @author Alberto Zapardiel Fernández
*/
@RequiredArgsConstructor
public class EventUseCase {

    /**
     * The event repository port
     */
    private final EventRepositoryPort eventRepositoryPort;

    /**
     * Get all events
     *
     * @return the list of events
     */
    public List<EventModel> getAllEvents() {
        return eventRepositoryPort.findAll();
    }

    /**
     * Get an event by id
     *
     * @param id the id
     * @return the event model
     */
    public Optional<EventModel> getEventById(Long id) {
        return eventRepositoryPort.findById(id);
    }

    /**
     * Create an event
     *
     * @param event the event
     * @return the event model
     */
    public EventModel createEvent(EventModel event) {
        return eventRepositoryPort.save(event);
    }

    /**
     * Delete an event
     *
     * @param id the id
     */
    public void deleteEvent(Long id) {
        eventRepositoryPort.deleteById(id);
    }
}