package fr.cda.cinemacda4.service;

import fr.cda.cinemacda4.entity.Film;
import fr.cda.cinemacda4.entity.Realisateur;
import fr.cda.cinemacda4.repository.RealisateurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RealisateurService {
    private final RealisateurRepository realisateurRepository;

    private final FilmService filmService;

    public RealisateurService(
            RealisateurRepository realisateurRepository,
            FilmService filmService
    ) {
        this.realisateurRepository = realisateurRepository;
        this.filmService = filmService;
    }

    public List<Realisateur> findAll() {
        return realisateurRepository.findAll();
    }

    public Realisateur save(Realisateur realisateur) {
        return realisateurRepository.save(realisateur);
    }

    public Realisateur findById(int integer) {
        return realisateurRepository
                .findById(integer)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Realisateur not found"
                        )
                );
    }

    public void deleteById(Integer id) {
        this.findById(id);


        List<Film> filmsAvecCeRealisateur = filmService.findAllByRealisateurId(id);

        filmsAvecCeRealisateur.forEach(
                film -> {
                    film.setRealisateur(null);
                    filmService.save(film);
                }
        );

        realisateurRepository.deleteById(id);
    }
}