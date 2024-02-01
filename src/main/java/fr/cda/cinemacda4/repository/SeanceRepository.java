package fr.cda.cinemacda4.repository;

import fr.cda.cinemacda4.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SeanceRepository extends JpaRepository<Seance, Integer> {
}
