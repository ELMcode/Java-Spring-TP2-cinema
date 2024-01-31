package fr.cda.cinemacda4.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmSansActeursDto {
    private String titre;
    private LocalDate dateSortie;
    private RealisateurSansFilmsDto realisateur;
}