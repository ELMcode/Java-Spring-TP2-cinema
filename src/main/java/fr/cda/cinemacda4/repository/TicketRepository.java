package fr.cda.cinemacda4.repository;

import fr.cda.cinemacda4.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findBySeanceIdAndIsReservedTrue(Integer seanceId);
}