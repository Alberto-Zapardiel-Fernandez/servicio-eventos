package com.application.mappers;

import com.application.controller.dto.EventDTO;
import com.domain.models.EventModel;
import org.mapstruct.Mapper;

import java.util.List;

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

    /**
     * List of EventModel to List of EventDTO
     *
     * @param events the list of EventModel
     * @return the list of EventDTO
     */
    default List<EventDTO> eventModelListToEventDTOList(List<EventModel> events){
        return events.stream().map(this::eventModelToEventDTO).toList();
    }

    /**
     * List of EventDTO to List of EventModel
     *
     * @param events the list of EventDTO
     * @return the list of EventModel
     */
    default List<EventModel> eventDTOListToEventModelList(List<EventDTO> events) {
        return events.stream().map(this::eventDTOToEventModel).toList();
    }
}
