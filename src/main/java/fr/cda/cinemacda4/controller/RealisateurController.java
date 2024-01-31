package fr.cda.cinemacda4.controller;

import fr.cda.cinemacda4.entity.Realisateur;
import fr.cda.cinemacda4.service.RealisateurService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realisateurs")
public class RealisateurController {

    private final RealisateurService realisateurService;

    public RealisateurController(RealisateurService realisateurService) {
        this.realisateurService = realisateurService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Realisateur> findAll() {
        return realisateurService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Realisateur save(@RequestBody Realisateur realisateur) {
        return realisateurService.save(realisateur);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Realisateur findById(@PathVariable Integer id) {
        return realisateurService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        realisateurService.deleteById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Realisateur update(@RequestBody Realisateur realisateur) {
        return realisateurService.update(realisateur);
    }

    @GetMapping(path = "/search")
    public Realisateur findByName(@RequestParam String nom) {
        return realisateurService.findByName(nom);
    }
}