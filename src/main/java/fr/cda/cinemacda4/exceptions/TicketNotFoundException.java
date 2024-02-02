package fr.cda.cinemacda4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TicketNotFoundException  extends RuntimeException {
    public TicketNotFoundException(Integer idTicket) {
        super("Aucun ticket avec l'ID " + idTicket);
    }
}
