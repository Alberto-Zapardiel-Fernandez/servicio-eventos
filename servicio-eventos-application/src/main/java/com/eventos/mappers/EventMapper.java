package com.eventos.mappers;

import com.eventos.model.EventModel;
import com.eventos.rest.dto.EventDTO;
import org.mapstruct.Mapper;

/**
 * Event Mapper
 *
 * @author Alberto Zapardiel Fernández
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

    /**
     * EventDTO to Event Model
     *
     * @param body the EventDTO
     * @return the Event model
     */
    EventModel eventDTOToEventModel(EventDTO body);

    /**
     * Event Model to EventDTO
     *
     * @param body the event model
     * @return the eventDTO
     */
    EventDTO eventModelToEventDTO(EventModel body);
}
