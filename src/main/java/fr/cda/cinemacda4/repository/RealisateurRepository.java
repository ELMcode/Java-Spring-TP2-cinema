package fr.cda.cinemacda4.repository;

import fr.cda.cinemacda4.entity.Realisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RealisateurRepository extends JpaRepository<Realisateur, Integer> {
}
