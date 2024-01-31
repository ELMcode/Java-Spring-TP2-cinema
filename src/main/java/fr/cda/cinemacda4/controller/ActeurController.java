package fr.cda.cinemacda4.controller;

import fr.cda.cinemacda4.entity.Acteur;
import fr.cda.cinemacda4.service.ActeurService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acteurs")
public class ActeurController {

    private final ActeurService acteurService;


    public ActeurController(ActeurService acteurService) {
        this.acteurService = acteurService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Acteur> findAll() {
        return acteurService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Acteur save(@RequestBody Acteur acteur) {
        return acteurService.save(acteur);
    }

    @GetMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Acteur findById(@PathVariable Integer id) {
        return acteurService.findById(id);
    }

    @DeleteMapping(path ="/{id}")
    public void deleteById(@PathVariable Integer id) {
        acteurService.deleteById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Acteur update(@RequestBody Acteur acteur) {
        return acteurService.update(acteur);
    }

    @GetMapping(path = "/search")
    public Acteur findByName(@RequestParam String nom) {
        return acteurService.findByName(nom);
    }
}
