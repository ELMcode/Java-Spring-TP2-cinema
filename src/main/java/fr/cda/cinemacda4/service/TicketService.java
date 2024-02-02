package fr.cda.cinemacda4.service;

import fr.cda.cinemacda4.dto.TicketCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.cda.cinemacda4.entity.Seance;
import fr.cda.cinemacda4.entity.Ticket;
import fr.cda.cinemacda4.exceptions.SeanceNotFoundException;
import fr.cda.cinemacda4.repository.SeanceRepository;
import fr.cda.cinemacda4.repository.TicketRepository;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SeanceRepository seanceRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, SeanceRepository seanceRepository) {
        this.ticketRepository = ticketRepository;
        this.seanceRepository = seanceRepository;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket save(TicketCreationDto ticketCreationDto) {

        verifyTicket(ticketCreationDto);

        Seance seance = seanceRepository.findById(ticketCreationDto.getSeance().getId())
                .orElseThrow(() -> new SeanceNotFoundException(ticketCreationDto.getSeance().getId()));

        Ticket ticket = new Ticket();
        ticket.setSeance(seance);
        ticket.setNomClient(ticketCreationDto.getNomClient());
        ticket.setNbPlaces(ticketCreationDto.getNbPlaces());

        return ticketRepository.save(ticket);
    }

    private void verifyTicket(TicketCreationDto ticketCreationDto) {
       // TODO : a faire -> verifier si dispo etc
    }

    public void deleteById(Integer id) {
        Ticket ticket = findById(id);
        ticketRepository.delete(ticket);
    }

    public Ticket findById(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new SeanceNotFoundException(id));
    }


}
