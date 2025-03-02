package com.eventos.rest;

import com.eventos.mappers.EventMapper;
import com.eventos.model.EventModel;
import com.eventos.rest.api.EventAPI;
import com.eventos.rest.dto.EventDTO;
import com.eventos.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class EventController implements EventAPI {

    /**
     * Event mapper
     */
    private EventMapper mapper;

    /**
     * Event Service
     */
    private EventService eventService;

    /**
     * GET /event : Obtiene la lista de eventos.
     *
     * @return Respuesta de la lista de eventos. (status code 200)
     * or Solicitud incorrecta. (status code 400)
     * or Error interno del servidor. (status code 500)
     */
    @Override
    public ResponseEntity<List<EventDTO>> _getEventList() {
        return null;
    }

    /**
     * POST /event : Crea un nuevo evento.
     *
     * @param body (required)
     * @return (status code 200)
     * or Solicitud incorrecta. (status code 400)
     * or Error interno del servidor. (status code 500)
     */
    @Override
    public ResponseEntity<EventDTO> _insertEvent(EventDTO body) {
        EventModel eventModel = mapper.eventDTOToEventModel(body);
        Optional<EventModel> response = eventService.createEvent(eventModel);
        return response.map(value -> ResponseEntity.ok(mapper.eventModelToEventDTO(value))).orElse(null);
    }
}
