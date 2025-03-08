package com.application.ports.in;

import com.domain.models.EventModel;

import java.util.List;
import java.util.Optional;

/**
 * Event Service
 *
 * @author Alberto Zapardiel Fernández
*/
public interface EventService {

    /**
     * Get all events
     *
     * @return the list of events
     */
    List<EventModel> getAllEvents();

    /**
     * Get an event by id
     *
     * @param id the id
     * @return the event model
     */
    Optional<EventModel> getEventById(Long id);

    /**
     * Create an event
     *
     * @param event the event
     * @return the event model
     */
    EventModel createEvent(EventModel event);

    /**
     * Delete an event
     *
     * @param id the id
     */
    void deleteEvent(Long id);
}
