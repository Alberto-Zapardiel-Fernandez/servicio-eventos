package com.infrastructure.mappers;

import com.domain.models.EventModel;
import com.infrastructure.entity.Event;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Event Entity Mapper
 *
 * @author Alberto Zapardiel Fernández
 */
@Mapper(componentModel = "spring")
public interface EventEntityMapper {

    /**
     * Event entity to Event model
     *
     * @param event the event entity
     * @return the event model
     */
    EventModel entityToModel(Event event);

    /**
     * Event entity list to an Event model list
     *
     * @param events the event entity list
     * @return the event model list
     */
    default List<EventModel> entityToModelList(List<Event> events){
        return events.stream().map(this::entityToModel).toList();
    }

    /**
     * Event model to Event entity
     *
     * @param eventModel the event model
     * @return the event entity
     */
    Event modelToEntity(EventModel eventModel);
}
