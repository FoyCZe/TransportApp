package com.company.transportapp.model.entities;

import com.company.transportapp.model.enums.TrailerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trailers")
@Getter
@Setter
@NoArgsConstructor
public class Trailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String plateNumber; // SPZ návěsu

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrailerType type; // Typ návěsu (TRAILER, SEMITRAILER)

    @Column(length = 50)
    private String brand; // Značka návěsu (např. Schmitz, Krone)

    @Column(length = 50)
    private String subType; // Podtyp návěsu (nosič kontejnerů, frigo)

    @Column(length = 255)
    private String notes; // Poznámky k návěsu (např. servis, přidělení, platnost STK)
}
