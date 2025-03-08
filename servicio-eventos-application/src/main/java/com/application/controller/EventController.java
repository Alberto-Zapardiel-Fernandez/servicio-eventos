package com.application.controller;

import com.application.controller.api.EventApi;
import com.application.controller.dto.EventDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController implements EventApi {
    /**
     * GET /event : Obtiene la lista de eventos.
     *
     * @return Respuesta de la lista de eventos. (status code 200)
     * or Solicitud incorrecta. (status code 400)
     * or Error interno del servidor. (status code 500)
     */
    @Override
    public ResponseEntity<List<EventDTO>> getEventList() {
        return null;
    }

    /**
     * POST /event : Crea un nuevo evento.
     *
     * @param eventDTO (required)
     * @return Evento creado exitosamente. (status code 201)
     * or Solicitud incorrecta. (status code 400)
     * or Error interno del servidor. (status code 500)
     */
    @Override
    public ResponseEntity<EventDTO> insertEvent(EventDTO eventDTO) {
        return null;
    }
}
