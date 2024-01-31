package fr.cda.cinemacda4.dto;

import lombok.Data;
import java.util.List;

@Data
public class ActeurDetailDto {
    private String nom;
    private String prenom;
    private List<FilmSansActeursDto> films;
}