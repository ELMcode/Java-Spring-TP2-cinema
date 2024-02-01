package fr.cda.cinemacda4.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmSansActeursNiRealisateurDto {
    private String titre;
    private int duree;
    private LocalDate dateSortie;
}
