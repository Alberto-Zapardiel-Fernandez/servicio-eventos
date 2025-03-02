package com.eventos.mappers;

import com.eventos.entities.Event;
import com.eventos.model.EventModel;
import org.mapstruct.Mapper;

/**
 * Event Mapper
 *
 * @author Alberto Zapardiel Fernández
*/
@Mapper(componentModel = "spring")
public interface EventModelMapper {

    /**
     * Event mapper from EventModel to Event
     * @param eventModel the model
     * @return the entity
     */
    Event toEntity(EventModel eventModel);

    /**
     * Event mapper from entity to model
     * @param event the entity
     * @return the model
     */
    EventModel toModel(Event event);
}
