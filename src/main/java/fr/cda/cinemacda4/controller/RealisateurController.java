package fr.cda.cinemacda4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.cinemacda4.dto.RealisateurDetailDto;
import fr.cda.cinemacda4.entity.Realisateur;
import fr.cda.cinemacda4.service.RealisateurService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/realisateurs")
public class RealisateurController {
    private final RealisateurService realisateurService;

    private final ObjectMapper objectMapper;

    public RealisateurController(RealisateurService realisateurService, ObjectMapper objectMapper) {
        this.realisateurService = realisateurService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public RealisateurDetailDto findById(@PathVariable int id) {
        Realisateur realisateur = realisateurService.findById(id);
        return objectMapper.convertValue(realisateur, RealisateurDetailDto.class);
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

}
