package com.company.transportapp.model.entities;


import com.company.transportapp.model.enums.Enums;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="transports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(optional=false)
    @JoinColumn(name="driver_id")
    private Employee driver;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Enums.TransportType type;  //CONTAINER/REEFER

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Enums.Direction direction;  //IMPORT/EXPORT

    // Čísla kontejnerů / Release kódy
    private String containerNumber;
    private String releaseCode;
    private String containerNumber2;
    private String releaseCode2;

    private String containerSize; // Velikost bedny 40HC, 20DV atd..
    private String sealNumber;  // Číslo plomby

    private Double cargoWeight;  // Hmotnost nákladu

    // Údaje o zadavateli
    private String clientName;
    private String clientAddress;
    private String clientIco;
    private Double clientPrice;

    // Stav přepravy
    @Column(nullable=false)
    private String status;  // Vytvořeno, Probíhá, Dokončeno, Zrušeno

    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    @Column(length=255)
    private String driverNotes;

    @Column(length=255)
    private String dispatcherNotes;

    @OneToMany(mappedBy="transport", cascade=CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Stop> stops = new ArrayList<>();
}
