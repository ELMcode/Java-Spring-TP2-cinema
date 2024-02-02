package fr.cda.cinemacda4.dto;

import lombok.Data;

@Data
public class TicketDto {
    private Integer id;
    private SeanceId seance;
    private String nomClient;
    private Integer nbPlaces;

    @Data
    public static class SeanceId {
        private Integer id;
    }
}
