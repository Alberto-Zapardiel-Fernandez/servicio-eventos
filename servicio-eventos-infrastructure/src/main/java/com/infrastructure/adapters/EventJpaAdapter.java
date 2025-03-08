package com.infrastructure.adapters;

import com.domain.models.EventModel;
import com.domain.ports.out.EventRepositoryPort;
import com.infrastructure.entities.Event;
import com.infrastructure.mappers.EventEntityMapper;
import com.infrastructure.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * EventJpaAdapter
 *
 * @author Alberto Zapardiel Fernández
 */
@Repository
@RequiredArgsConstructor
public class EventJpaAdapter implements EventRepositoryPort {

    /**
     * The event repository
     */
    private final EventRepository eventRepository;

    /**
     * The event entity mapper
     */
    private final EventEntityMapper eventEntityMapper;

    /**
     * Find all events
     *
     * @return the list of events
     */
    @Override
    public List<EventModel> findAll() {
        return eventEntityMapper.entityToModelList(eventRepository.findAll());
    }

    /**
     * Find an event by id
     *
     * @param id the id
     * @return the event model
     */
    @Override
    public Optional<EventModel> findById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        EventModel model = eventEntityMapper.entityToModel(event.orElse(null));
        return Optional.ofNullable(model);
    }

    /**
     * Save an event
     *
     * @param eventModel the event model
     * @return the event model
     */
    @Override
    public EventModel save(EventModel eventModel) {
        return eventEntityMapper.entityToModel(eventRepository.save(eventEntityMapper.modelToEntity(eventModel)));
    }

    /**
     * Delete an event by id
     *
     * @param id the id
     */
    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

}