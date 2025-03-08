package com.application.controller;

import com.application.controller.api.EventApi;
import com.application.controller.dto.EventDTO;
import com.application.ports.in.EventService;
import com.domain.models.EventModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController implements EventApi {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public ResponseEntity<List<EventDTO>> getEventList() {
        List<EventModel> events = eventService.getAllEvents();
        List<EventDTO> eventDTOs = events.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(eventDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EventDTO> insertEvent(EventDTO eventDTO) {
        EventModel eventModel = mapToModel(eventDTO);
        EventModel createdEvent = eventService.createEvent(eventModel);
        EventDTO createdEventDTO = mapToDTO(createdEvent);
        return new ResponseEntity<>(createdEventDTO, HttpStatus.CREATED);
    }

    private EventDTO mapToDTO(EventModel eventModel) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventModel.getId().intValue()); // Convierte Long a Integer
        eventDTO.setName(eventModel.getName());
        eventDTO.setDescription(eventModel.getDescription());
        eventDTO.setDate(LocalDate.parse(eventModel.getDate())); // Convierte String a LocalDate
        eventDTO.setTime(eventModel.getTime());
        eventDTO.setLocation(eventModel.getLocation());
        eventDTO.setCategory(eventModel.getCategory());
        eventDTO.setCapacity(eventModel.getCapacity());
        return eventDTO;
    }

    private EventModel mapToModel(EventDTO eventDTO) {
        EventModel eventModel = new EventModel();
        eventModel.setId(eventDTO.getId().longValue()); // Convierte Integer a Long
        eventModel.setName(eventDTO.getName());
        eventModel.setDescription(eventDTO.getDescription());
        eventModel.setDate(eventDTO.getDate().toString()); // Convierte LocalDate a String
        eventModel.setTime(eventDTO.getTime());
        eventModel.setLocation(eventDTO.getLocation());
        eventModel.setCategory(eventDTO.getCategory());
        eventModel.setCapacity(eventDTO.getCapacity());
        return eventModel;
    }
}