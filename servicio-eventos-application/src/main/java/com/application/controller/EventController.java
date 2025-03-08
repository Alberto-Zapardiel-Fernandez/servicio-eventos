package com.application.controller;

import com.application.controller.api.EventApi;
import com.application.controller.dto.EventDTO;
import com.application.ports.in.EventService;
import com.domain.models.EventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventService eventService;

    @Override
    public ResponseEntity<List<EventDTO>> getEventList() {
        List<EventModel> events = eventService.getAllEvents();
        List<EventDTO> eventDTOs = events.stream()
                .map(this::mapToDTO).toList();
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