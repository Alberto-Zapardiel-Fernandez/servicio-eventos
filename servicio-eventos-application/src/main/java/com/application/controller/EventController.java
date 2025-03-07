package com.application.controller;

import com.application.controller.api.EventApi;
import com.application.controller.dto.EventDTO;
import com.application.mappers.EventMapper;
import com.application.ports.in.EventService;
import com.domain.models.EventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 *  Event Controller
 * <p>
 * This class is the implementation of the EventApi interface
 * <p>
 * It is used to manage the events
 * <p>
 * It has the following methods:
 * - getEventList: Get all the events
 * - insertEvent: Insert a new event
 * <p>
 * It uses the EventService to manage the events
 * <p>
 * It uses the EventMapper to map the EventDTO and EventModel
 *
 * @author Alberto Zapardiel Fernández
 */
@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    /**
     * The event Service
     */
    private final EventService eventService;

    /**
     * The event mapper
     */
    private final EventMapper eventMapper;

    /**
     * DELETE /event : Delete un nuevo evento.
     *
     * @param id (required)
     * @return Evento creado exitosamente. (status code 200)
     * or Solicitud incorrecta. (status code 400)
     * or Error interno del servidor. (status code 500)
     */
    @Override
    public ResponseEntity<Void> deleteEvent(Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /event/{id} : Obtiene un evento.
     *
     * @param id (required)
     * @return (status code 200)
     * or Solicitud incorrecta. (status code 400)
     * or Error interno del servidor. (status code 500)
     */
    @Override
    public ResponseEntity<EventDTO> getEvent(Long id) {
        EventDTO eventDTO = eventMapper.eventModelToEventDTO(eventService.getEventById(id).orElse(null));
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    /**
     * Get all the events
     *
     * @return the list of events
     */
    @Override
    public ResponseEntity<List<EventDTO>> getEventList() {
        List<EventDTO> eventDTOs = eventMapper.eventModelListToEventDTOList(eventService.getAllEvents());
        return new ResponseEntity<>(eventDTOs, HttpStatus.OK);
    }

    /**
     * Insert a new event
     *
     * @param eventDTO the eventDTO
     * @return the created event
     */
    @Override
    public ResponseEntity<EventDTO> insertEvent(EventDTO eventDTO) {
        EventModel createdEvent = eventService.createEvent(eventMapper.eventDTOToEventModel(eventDTO));
        EventDTO createdEventDTO = eventMapper.eventModelToEventDTO(createdEvent);
        return new ResponseEntity<>(createdEventDTO, HttpStatus.CREATED);
    }

    /**
     * PUT /event : Update un nuevo evento.
     *
     * @param eventDTO (required)
     * @return Evento creado exitosamente. (status code 201)
     * or Solicitud incorrecta. (status code 400)
     * or Error interno del servidor. (status code 500)
     */
    @Override
    public ResponseEntity<EventDTO> updateEvent(EventDTO eventDTO) {
        EventDTO updatedEventDTO = eventMapper.eventModelToEventDTO(eventService.createEvent(eventMapper.eventDTOToEventModel(eventDTO)));
        return new ResponseEntity<>(updatedEventDTO, HttpStatus.CREATED);
    }

}