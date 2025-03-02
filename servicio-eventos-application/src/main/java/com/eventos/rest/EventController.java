package com.eventos.rest;

import com.eventos.rest.api.EventAPI;
import com.eventos.rest.dto.EventDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController implements EventAPI {

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
    public ResponseEntity<Void> _insertEvent(EventDTO body) {
        return null;
    }
}
