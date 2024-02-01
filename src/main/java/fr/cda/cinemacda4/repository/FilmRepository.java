package fr.cda.cinemacda4.repository;

import fr.cda.cinemacda4.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    // SELECT * FROM film WHERE titre like ....
    Optional<Film> findByTitre(String titre);

    Optional<Film> findByDateSortieAfter(LocalDate date);

    // SELECT * FROM film WHERE realisateur_id IS ...
    Optional<List<Film>> findAllByRealisateurId(Integer id);

}