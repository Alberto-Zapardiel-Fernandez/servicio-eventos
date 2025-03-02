package com.eventos.services;

import com.eventos.model.EventModel;

import java.util.Optional;

/**
 * Event Service
 *
 * @author Alberto Zapardiel Fernández
 */
public interface EventService {

    /**
     * Method to create an Event
     * @param eventModel the event
     * @return the Event
     */
    Optional<EventModel> createEvent(EventModel eventModel);
}
