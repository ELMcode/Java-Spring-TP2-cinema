package fr.cda.cinemacda4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeanceNotFoundException  extends RuntimeException {
    public SeanceNotFoundException(Integer idSalle) {
        super("Aucune seance avec l'ID " + idSalle);
    }
}
