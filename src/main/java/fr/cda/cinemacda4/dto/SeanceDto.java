package fr.cda.cinemacda4.dto;

import fr.cda.cinemacda4.entity.Salle;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeanceDto {
    private Integer id;
    private FilmMiniDto film;
    private Salle salle;
    private LocalDateTime date;
    private Double prix;
    private Integer placesDisponibles;
}