package com.domain.ports.out;

import com.domain.models.EventModel;
import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {

    List<EventModel> findAll();
    Optional<EventModel> findById(Long id);
    EventModel save(EventModel event);
    void deleteById(Long id);
}