package fr.cda.cinemacda4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SalleNotFoundException  extends RuntimeException {
    public SalleNotFoundException(Integer idSalle) {
        super("Aucune salle avec l'ID " + idSalle);
    }
}
