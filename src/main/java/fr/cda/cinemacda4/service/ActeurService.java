package fr.cda.cinemacda4.service;

import fr.cda.cinemacda4.entity.Acteur;
import fr.cda.cinemacda4.repository.ActeurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActeurService {
    private final ActeurRepository acteurRepository;

    public ActeurService(ActeurRepository acteurRepository) {
        this.acteurRepository = acteurRepository;
    }

    public Acteur save(Acteur entity) {
        return acteurRepository.save(entity);
    }

    public Acteur findById(Integer integer) {
        return acteurRepository.findById(integer).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Acteur non trouvé"
                )
        );
    }

    public void delete(Acteur acteur) {
        this.findById(acteur.getId());
        acteurRepository.delete(acteur);
    }

    public List<Acteur> findAll() {
        return acteurRepository.findAll();
    }
}