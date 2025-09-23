package com.company.transportapp.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="stops")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="transport_id")
    private Transport transport;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Enums.StopType stopType;  // LOADING/UNLOADING

    // Adresa
    private String companyName;
    private String street;
    private String buildingNumber;
    private String orientationNumber;
    private String zipCode;
    private String city;
    private String countryCode;

    // Slot (plán vykládky/nakládky)
    private LocalDateTime slotDateTime;

    // Časy (příjezd/odjezd)
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    // Čekací doba (doba strávená na nakl/vykl uložená ve formátu HH:MM)
    private String waitingTime;

    // Palety a teplota pro reefers
    private Integer palletsGiven;  // Palety výměna z auta do skladu
    private Integer palletsReceived;  // Palety výměna ze skladu na auto
    private Integer palletsLoaded;  // Celkem naloženo palet
    private Integer palletsUnloaded;  // Celkem vyloženo palet
    private Double temperature;  // Teplota

    @Column(length=255)
    private String driverNotes;

    @Column(length=255)
    private String dispatcherNotes;
}
