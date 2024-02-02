package fr.cda.cinemacda4.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeanceCreationDto {
    private FilmId film;
    private SalleId salle;
    private LocalDateTime date;
    private Double prix;
    private String nomClient;
    private Integer nbPlaces;

    @Data
    public static class FilmId {
        private Integer id;
    }

    @Data
    public static class SalleId {
        private Integer id;
    }
}
