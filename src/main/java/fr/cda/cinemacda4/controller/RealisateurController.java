package fr.cda.cinemacda4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.cinemacda4.dto.FilmSansActeursNiRealisateurDto;
import fr.cda.cinemacda4.dto.RealisateurAvecFilmsDto;
import fr.cda.cinemacda4.entity.Film;
import fr.cda.cinemacda4.entity.Realisateur;
import fr.cda.cinemacda4.service.RealisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realisateurs")
public class RealisateurController {
    private final RealisateurService realisateurService;

    private final ObjectMapper objectMapper;

    public RealisateurController(RealisateurService realisateurService, ObjectMapper objectMapper) {
        this.realisateurService = realisateurService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Realisateur> findAll() {
        return realisateurService.findAll();
    }

    @GetMapping("/{id}")
    public RealisateurAvecFilmsDto findById(@PathVariable int id) {
        return realisateurService.findRealisateurWithFilm(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        realisateurService.deleteById(id);
    }

    @PostMapping
    public Realisateur save(@RequestBody Realisateur realisateur) {
        return realisateurService.save(realisateur);
    }

    @PutMapping
    public Realisateur update(@RequestBody Realisateur realisateur) {
        return realisateurService.save(realisateur);
    }

    @GetMapping("/{id}/films")
    public List<FilmSansActeursNiRealisateurDto> findFilmsByRealisateurId(@PathVariable Integer id) {
        List<Film> filmsDuRealisateur = realisateurService.findFilmsByRealisateurId(id);

        return filmsDuRealisateur.stream().map(
                film -> objectMapper.convertValue(film, FilmSansActeursNiRealisateurDto.class)
        ).toList();
    }

}