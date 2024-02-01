package fr.cda.cinemacda4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.cinemacda4.entity.Salle;
import fr.cda.cinemacda4.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
public class SalleController {
    private final SalleService salleService;

    // private final ObjectMapper objectMapper;

    @Autowired
    public SalleController(
            SalleService salleService
           //  ObjectMapper objectMapper
    ) {
        this.salleService = salleService;
       // this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Salle> findAll() {
        return salleService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Salle save(@RequestBody Salle salle) {
        return salleService.save(salle);
    }

    @GetMapping("/{id}")
    public Salle findById(@PathVariable Integer id) {
        return salleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        salleService.deleteById(id);
    }

    @PutMapping
    public Salle update(@RequestBody Salle salle) {
        return salleService.update(salle);
    }

    @GetMapping("/search") // /salles/search?nom=GrandeSalle
    public Salle findByNom(@RequestParam String nom) {
        return salleService.findByNom(nom);
    }
}