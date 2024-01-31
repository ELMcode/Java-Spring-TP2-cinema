package fr.cda.cinemacda4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.cinemacda4.dto.ActeurDetailDto;
import fr.cda.cinemacda4.dto.ActeurListDto;
import fr.cda.cinemacda4.entity.Acteur;
import fr.cda.cinemacda4.service.ActeurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acteurs")
public class ActeurController {
    private final ActeurService acteurService;
    private final ObjectMapper objectMapper;


    public ActeurController(
            ActeurService acteurService,
            ObjectMapper objectMapper
    ) {
        this.acteurService = acteurService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public Acteur save(@RequestBody Acteur entity) {
        return acteurService.save(entity);
    }

    @GetMapping("/{id}")
    public ActeurDetailDto findById(@PathVariable Integer id) {
        Acteur acteur = acteurService.findById(id);
        return objectMapper.convertValue(acteur,ActeurDetailDto.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestBody Acteur acteur) {
        acteurService.delete(acteur);
    }

    @GetMapping
    public List<ActeurListDto> findAll() {
        return acteurService.findAll().stream().map(
                acteur -> objectMapper.convertValue(acteur, ActeurListDto.class)
        ).toList();
    }
}
