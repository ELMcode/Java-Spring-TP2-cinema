package fr.cda.cinemacda4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import fr.cda.cinemacda4.dto.TicketCreationDto;
import fr.cda.cinemacda4.dto.TicketDto;
import fr.cda.cinemacda4.entity.Ticket;
import fr.cda.cinemacda4.service.TicketService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final ObjectMapper objectMapper;

    public TicketController(TicketService ticketService, ObjectMapper objectMapper) {
        this.ticketService = ticketService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<TicketDto> findAll() {
        return ticketService.findAll().stream().map(
                        ticket -> objectMapper.convertValue(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDto save(@RequestBody TicketCreationDto ticketCreationDto) {
        Ticket ticket = ticketService.save(ticketCreationDto);
        return objectMapper.convertValue(ticket, TicketDto.class);
    }

    @GetMapping("/{id}")
    public TicketDto findById(@PathVariable Integer id) {
        Ticket ticket = ticketService.findById(id);
        return objectMapper.convertValue(ticket, TicketDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        ticketService.deleteById(id);
    }

}
