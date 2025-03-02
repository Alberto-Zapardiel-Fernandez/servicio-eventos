package com.eventos.services;

import com.eventos.entities.Event;
import com.eventos.mappers.EventModelMapper;
import com.eventos.model.EventModel;
import com.eventos.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Event Service Impl
 *
 * @author Alberto Zapardiel Fernández
 */
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    /**
     * Event Repository
     */
    private EventRepository eventRepository;

    /**
     * Event Mapper
     */
    private EventModelMapper eventModelMapper;

    /**
     * Method to create an Event
     *
     * @param eventModel the event
     * @return the Event
     */
    @Override
    public Optional<EventModel> createEvent(EventModel eventModel) {
        Event eventEntity = eventModelMapper.toEntity(eventModel);
        Event event = eventRepository.save(eventEntity);
        return Optional.ofNullable(eventModelMapper.toModel(event));
    }
}