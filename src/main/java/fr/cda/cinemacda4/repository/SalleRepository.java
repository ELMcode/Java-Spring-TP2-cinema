package fr.cda.cinemacda4.repository;

import fr.cda.cinemacda4.entity.Film;
import fr.cda.cinemacda4.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalleRepository extends JpaRepository<Salle, Integer> {
    Optional<Salle> findByNom(String nom);
}