package com.application.ports.in;

import com.domain.models.EventModel;
import com.domain.usecases.EventUseCase;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventUseCase eventUseCase;

    public EventServiceImpl(EventUseCase eventUseCase) {
        this.eventUseCase = eventUseCase;
    }

    @Override
    public List<EventModel> getAllEvents() {
        return eventUseCase.getAllEvents();
    }

    @Override
    public Optional<EventModel> getEventById(Long id) {
        return eventUseCase.getEventById(id);
    }

    @Override
    public EventModel createEvent(EventModel event) {
        return eventUseCase.createEvent(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventUseCase.deleteEvent(id);
    }
}