package fr.cda.cinemacda4.service;

import fr.cda.cinemacda4.entity.Acteur;
import fr.cda.cinemacda4.entity.Film;
import fr.cda.cinemacda4.exceptions.BadRequestException;
import fr.cda.cinemacda4.exceptions.FilmNotFoundException;
import fr.cda.cinemacda4.repository.FilmRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class FilmService {
    private final FilmRepository filmRepository;

    private final ActeurService acteurService;

    public FilmService(FilmRepository filmRepository, ActeurService acteurService) {
        this.filmRepository = filmRepository;
        this.acteurService = acteurService;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film save(Film film) throws BadRequestException {
        verifyFilm(film);

        return filmRepository.save(film);
    }

    private static void verifyFilm(Film film) {
        List<String> erreurs = new ArrayList<>();

        if (film.getTitre() == null) {
            erreurs.add("Le titre est obligatoire");
        }

        if (film.getDateSortie() == null) {
            erreurs.add("La date de sortie est obligatoire");
        }

        if (film.getRealisateur() == null) {
            erreurs.add("Le réalisateur est obligatoire");
        }

        if (!erreurs.isEmpty()) {
            throw new BadRequestException(erreurs);
        }
    }

    public Film findById(Integer id) {
        return filmRepository.findById(id)
                .orElseThrow(
                        () -> new FilmNotFoundException(id)
                );
    }

    public void deleteById(Integer id) {
        Film film = this.findById(id);
        filmRepository.delete(film);
    }

    public Film update(Film film) {
        return filmRepository.save(film);
    }

    public Film findByTitre(String titre) {
        return filmRepository.findByTitre(titre)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Aucun film avec le titre : " + titre
                        )
                );
    }

    public List<Film> findAllByRealisateurId(Integer id) {
        return filmRepository.findAllByRealisateurId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun film ayant ce réalisateur"
                ));
    }

    public List<Acteur> findActeursByFilm(Integer id) {
        Film film = this.findById(id);
        return film.getActeurs();
    }


    public Film addActorToFilm(Integer id, Acteur acteur) {

        Film film = this.findById(id);
        acteur = acteurService.findById(acteur.getId());

        film.getActeurs().add(acteur);

        return this.save(film);
    }
}
