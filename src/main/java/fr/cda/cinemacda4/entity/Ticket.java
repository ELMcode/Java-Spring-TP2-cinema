package fr.cda.cinemacda4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "seance_id", referencedColumnName = "id")
    private Seance seance;

    private String nomClient;

    private Integer nbPlaces;

    private Boolean isReserved;
}