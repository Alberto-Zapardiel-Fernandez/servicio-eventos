package com.domain.ports.out;

import com.domain.models.EventModel;
import java.util.List;
import java.util.Optional;

/**
 * Event Repository Port
 *
 * @author Alberto Zapardiel Fernández
*/
public interface EventRepositoryPort {

    /**
     * Find all events
     *
     * @return the list of events
     */
    List<EventModel> findAll();

    /**
     * Find an event by id
     *
     * @param id the id
     * @return the event model
     */
    Optional<EventModel> findById(Long id);

    /**
     * Save an event
     *
     * @param event the event
     * @return the event model
     */
    EventModel save(EventModel event);

    /**
     * Delete an event by id
     *
     * @param id the id
     */
    void deleteById(Long id);
}