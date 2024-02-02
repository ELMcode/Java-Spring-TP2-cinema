package fr.cda.cinemacda4.service;

import fr.cda.cinemacda4.dto.SeanceCreationDto;
import fr.cda.cinemacda4.dto.TicketCreationDto;
import fr.cda.cinemacda4.entity.Film;
import fr.cda.cinemacda4.entity.Salle;
import fr.cda.cinemacda4.entity.Seance;
import fr.cda.cinemacda4.entity.Ticket;
import fr.cda.cinemacda4.exceptions.BadRequestException;
import fr.cda.cinemacda4.exceptions.FilmNotFoundException;
import fr.cda.cinemacda4.exceptions.SalleNotFoundException;
import fr.cda.cinemacda4.exceptions.SeanceNotFoundException;
import fr.cda.cinemacda4.repository.FilmRepository;
import fr.cda.cinemacda4.repository.SalleRepository;
import fr.cda.cinemacda4.repository.SeanceRepository;
import fr.cda.cinemacda4.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final SalleRepository salleRepository;
    private final FilmRepository filmRepository;
    private final TicketRepository ticketRepository;

    public SeanceService(SeanceRepository seanceRepository, SalleRepository salleRepository, FilmRepository filmRepository, TicketRepository ticketRepository) {
        this.seanceRepository = seanceRepository;
        this.salleRepository = salleRepository;
        this.filmRepository = filmRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    public Seance save(SeanceCreationDto seanceCreationDto) {

        verifySeance(seanceCreationDto);

        Film film = filmRepository.findById(seanceCreationDto.getFilm().getId())
                .orElseThrow(() -> new FilmNotFoundException(seanceCreationDto.getFilm().getId()));
        Salle salle = salleRepository.findById(seanceCreationDto.getSalle().getId())
                .orElseThrow(() -> new SalleNotFoundException(seanceCreationDto.getSalle().getId()));

        Seance seance = new Seance();
        seance.setFilm(film);
        seance.setSalle(salle);
        seance.setDate(seanceCreationDto.getDate());
        seance.setPrix(seanceCreationDto.getPrix());

        return seanceRepository.save(seance);
    }

    private void verifySeance(SeanceCreationDto seanceCreationDto) {
        List<String> errors = new ArrayList<>();

        if (seanceCreationDto.getDate() == null) {
            errors.add("La date est obligatoire");
        }

        if (seanceCreationDto.getPrix() == null || seanceCreationDto.getPrix() < 0) {
            errors.add("Le prix doit être positi");
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(errors);
        }
    }

    public Seance findById(Integer id) {
        return seanceRepository.findById(id)
                .orElseThrow(
                        () -> new SeanceNotFoundException(id)
                );
    }

    public Seance update(Integer id, SeanceCreationDto seanceCreationDto) {

        Seance existingSeance = seanceRepository.findById(id)
                .orElseThrow(() -> new SeanceNotFoundException(id));

        Film film = filmRepository.findById(seanceCreationDto.getFilm().getId())
                .orElseThrow(() -> new FilmNotFoundException(seanceCreationDto.getFilm().getId()));

        Salle salle = salleRepository.findById(seanceCreationDto.getSalle().getId())
                .orElseThrow(() -> new SalleNotFoundException(seanceCreationDto.getSalle().getId()));

        existingSeance.setFilm(film);
        existingSeance.setSalle(salle);
        existingSeance.setDate(seanceCreationDto.getDate());
        existingSeance.setPrix(seanceCreationDto.getPrix());

        return seanceRepository.save(existingSeance);
    }


    public void deleteById(Integer id) {
        Seance seance = findById(id);
        seanceRepository.delete(seance);
    }

    public Ticket reserveTicket(Integer seanceId, SeanceCreationDto reservationDto) {

        Seance seance = findById(seanceId);

        int placesDisponibles = calculerPlacesDispo(seance);

        if (reservationDto.getNbPlaces() > placesDisponibles) {
            List<String> errors = new ArrayList<>();
            errors.add("Pas assez de places disponibles");
            throw new BadRequestException(errors);
        }

        TicketCreationDto ticketDto = new TicketCreationDto();

        ticketDto.setSeance(new TicketCreationDto.SeanceId());
        ticketDto.setNomClient(reservationDto.getNomClient());
        ticketDto.setNbPlaces(reservationDto.getNbPlaces());

        Ticket ticket = convertToEntity(ticketDto);
        return ticketRepository.save(ticket);
    }

    private Ticket convertToEntity(TicketCreationDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setNomClient(ticketDto.getNomClient());
        ticket.setNbPlaces(ticketDto.getNbPlaces());

        Seance seance = seanceRepository.findById(ticketDto.getSeance().getId())
                .orElseThrow(() -> new SeanceNotFoundException(ticketDto.getSeance().getId()));

        ticket.setSeance(seance);
        ticket.setNbPlaces(ticketDto.getNbPlaces());
        ticket.setNomClient(ticketDto.getNomClient());

        return ticket;
    }

    public List<Ticket> getReservedTicketsForSeance(Integer seanceId) {
        return ticketRepository.findBySeanceIdAndIsReservedTrue(seanceId);
    }

    public int calculerPlacesDispo(Seance seance) {
    // TODO : a creer, pour le moment 100 pour test
        return 100;
    }
}
