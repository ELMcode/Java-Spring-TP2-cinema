package fr.cda.cinemacda4.service;

import fr.cda.cinemacda4.entity.Salle;
import fr.cda.cinemacda4.exceptions.BadRequestException;
import fr.cda.cinemacda4.exceptions.SalleNotFoundException;
import fr.cda.cinemacda4.repository.SalleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;

@Service
public class SalleService {

    private final SalleRepository salleRepository;

    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    public List<Salle> findAll() {
        return salleRepository.findAll();
    }

    public Salle save(Salle salle) throws BadRequestException {
        verifySalle(salle);

        return salleRepository.save(salle);
    }

    private static void verifySalle(Salle salle) {
        List<String> erreurs = new ArrayList<>();

        if (salle.getNom() == null) {
            erreurs.add("Le nom de la salle est obligatoire");
        }

        if (salle.getCapacite() == null) {
            erreurs.add("La capacité de la salle est obligatoire");
        }

        if (!erreurs.isEmpty()) {
            throw new BadRequestException(erreurs);
        }
    }

    public Salle findById(Integer id) {
        return salleRepository.findById(id)
                .orElseThrow(
                        () -> new SalleNotFoundException(id)
                );
    }

    public void deleteById(Integer id) {
        Salle salle = this.findById(id);
        salleRepository.delete(salle);
    }

    public Salle update(Salle salle) {
        verifySalle(salle);

        return salleRepository.save(salle);
    }

    public Salle findByNom (String nom) {
        return salleRepository.findByNom(nom)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Aucune salle avec le nom : " + nom
                        )
                );
    }
}