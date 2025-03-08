package com.infrastructure.adapters;

import com.domain.models.EventModel;
import com.domain.ports.out.EventRepositoryPort;
import com.infrastructure.entity.Event;
import com.infrastructure.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventJpaAdapter implements EventRepositoryPort {

    private final EventRepository eventRepository;

    public EventJpaAdapter(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventModel> findAll() {
        return eventRepository.findAll().stream().map(this::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<EventModel> findById(Long id) {
        return eventRepository.findById(id).map(this::mapToDomainEntity);
    }

    @Override
    public EventModel save(EventModel eventModel) {
        return mapToDomainEntity(eventRepository.save(mapToJpaEntity(eventModel)));
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    private EventModel mapToDomainEntity(Event event) {
        EventModel eventModel = new EventModel();
        eventModel.setId(event.getId());
        eventModel.setName(event.getName());
        eventModel.setDescription(event.getDescription());
        eventModel.setDate(event.getDate());
        eventModel.setTime(event.getTime());
        eventModel.setLocation(event.getLocation());
        eventModel.setCategory(event.getCategory());
        eventModel.setCapacity(event.getCapacity());
        return eventModel;
    }

    private Event mapToJpaEntity(EventModel eventModel) {
        Event event = new Event();
        event.setId(eventModel.getId());
        event.setName(eventModel.getName());
        event.setDescription(eventModel.getDescription());
        event.setDate(eventModel.getDate());
        event.setTime(eventModel.getTime());
        event.setLocation(eventModel.getLocation());
        event.setCategory(eventModel.getCategory());
        event.setCapacity(eventModel.getCapacity());
        return event;
    }
}