package fr.cda.cinemacda4.dto;

import fr.cda.cinemacda4.entity.Realisateur;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmSansActeurDto {
    private Integer id;
    private String titre;
    private LocalDate dateSortie;
    private Realisateur realisateur;
}
