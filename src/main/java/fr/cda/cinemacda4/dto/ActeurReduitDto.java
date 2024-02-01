package fr.cda.cinemacda4.dto;

import lombok.Data;

import java.util.List;

@Data
public class ActeurReduitDto {
    private Integer id;
    private String nom;
    private String prenom;
    private List<FilmSansActeurDto> films;
}
