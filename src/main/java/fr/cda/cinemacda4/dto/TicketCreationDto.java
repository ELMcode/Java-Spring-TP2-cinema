package fr.cda.cinemacda4.dto;

import lombok.Data;

@Data
public class TicketCreationDto {
    private SeanceId seance;
    private String nomClient;
    private Integer nbPlaces;

    @Data
    public static class SeanceId {
        private Integer id;
    }
}
