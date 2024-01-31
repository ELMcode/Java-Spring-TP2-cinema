package fr.cda.cinemacda4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.cinemacda4.dto.*;
import fr.cda.cinemacda4.entity.Film;
import fr.cda.cinemacda4.entity.Realisateur;
import fr.cda.cinemacda4.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    private final ObjectMapper objectMapper;

    public FilmController(
            FilmService filmService,
            ObjectMapper objectMapper
    ) {
        this.filmService = filmService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<FilmReduitDto> findAll() {
        return filmService.findAll().stream().map(
                film -> objectMapper.convertValue(film, FilmReduitDto.class)
        ).toList();
    }

    @PostMapping
    public Film save(@RequestBody Film film) {
        return filmService.save(film);
    }

    @GetMapping("/{id}") // /films/1
    public FilmCompletDto findById(@PathVariable Integer id) {
        Film film = filmService.findById(id);
        return objectMapper.convertValue(film, FilmCompletDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        filmService.deleteById(id);
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        return filmService.update(film);
    }

    @GetMapping("/search") // /film/search?titre=toto
    public Film findByTitre(@RequestParam String titre) {
        return filmService.findByTitre(titre);
    }

    @GetMapping("/{id}/acteurs")
    public List<ActeurSansIdDto> getActeursByFilmId(@PathVariable Integer id) {
        Film film = filmService.findById(id);
        return film.getActeurs().stream()
                .map(acteur -> objectMapper.convertValue(acteur, ActeurSansIdDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/realisateur")
    public RealisateurSansFilmsDto getRealisateurByFilmId(@PathVariable Integer id) {
        Film film = filmService.findById(id);
        Realisateur realisateur = film.getRealisateur();
        return objectMapper.convertValue(realisateur, RealisateurSansFilmsDto.class);
    }
}