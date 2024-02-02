package fr.cda.cinemacda4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.cinemacda4.dto.SeanceCreationDto;
import fr.cda.cinemacda4.dto.SeanceDto;
import fr.cda.cinemacda4.dto.TicketDto;
import fr.cda.cinemacda4.entity.Ticket;
import fr.cda.cinemacda4.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import fr.cda.cinemacda4.entity.Seance;
import fr.cda.cinemacda4.service.SeanceService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seances")
public class SeanceController {

    private final SeanceService seanceService;

    private final TicketService ticketService;
    private final ObjectMapper objectMapper;

    public SeanceController(
            SeanceService seanceService, TicketService ticketService,
            ObjectMapper objectMapper
    ) {
        this.seanceService = seanceService;
        this.ticketService = ticketService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<SeanceDto> findAll() {
        return seanceService.findAll().stream().map(
                seance -> objectMapper.convertValue(seance, SeanceDto.class))
                    .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeanceDto save(@RequestBody SeanceCreationDto seanceCreationDto) {

        Seance seance = seanceService.save(seanceCreationDto);

        return objectMapper.convertValue(seance, SeanceDto.class);
    }
    @GetMapping("/{id}")
    public SeanceDto findById(@PathVariable Integer id) {

        Seance seance = seanceService.findById(id);

        SeanceDto seanceDto = objectMapper.convertValue(seance, SeanceDto.class);

        seanceDto.setPlacesDisponibles(seanceService.calculerPlacesDispo(seance));

        return seanceDto;
    }

    @PutMapping("/{id}")
    public SeanceDto update(@PathVariable Integer id, @RequestBody SeanceCreationDto seanceCreationDto) {
        Seance updatedSeance = seanceService.update(id, seanceCreationDto);
        return objectMapper.convertValue(updatedSeance, SeanceDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        seanceService.deleteById(id);
    }

    @PostMapping("/{id}/reserver")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDto reserveTicket(@PathVariable Integer id, @RequestBody SeanceCreationDto reservationDto) {

        Ticket savedTicket = seanceService.reserveTicket(id, reservationDto);

        return objectMapper.convertValue(savedTicket, TicketDto.class);
    }

    @GetMapping("/{id}/tickets")
    public List<TicketDto> getReservedTicketsForSeance(@PathVariable Integer id) {

        List<Ticket> reservedTickets = seanceService.getReservedTicketsForSeance(id);

        return reservedTickets.stream()
                .map(ticket -> objectMapper.convertValue(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

}
