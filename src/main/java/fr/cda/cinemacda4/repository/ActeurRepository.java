package fr.cda.cinemacda4.repository;

import fr.cda.cinemacda4.entity.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActeurRepository extends JpaRepository<Acteur, Integer> {
    Optional<Acteur> findByNom(String nom);
}
