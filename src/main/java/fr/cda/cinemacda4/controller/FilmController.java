package fr.cda.cinemacda4.controller;

import fr.cda.cinemacda4.entity.Film;
import fr.cda.cinemacda4.service.FilmService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findAll() {
        return filmService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Film save(@RequestBody Film film) {
        return filmService.save(film);
    }

    @GetMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Film findById(@PathVariable Integer id) {
        return filmService.findById(id);
    }

    @DeleteMapping(path ="/{id}")
    public void deleteById(@PathVariable Integer id) {
        filmService.deleteById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Film update(@RequestBody Film film) {
        return filmService.update(film);
    }

    @GetMapping(path = "/search")
    public Film findByTitre(@RequestParam String titre) {
        return filmService.findByTitre(titre);
    }
}