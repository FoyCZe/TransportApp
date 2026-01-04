package com.company.transportapp.model.entities;

import com.company.transportapp.model.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trucks")
@Getter
@Setter
@NoArgsConstructor
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String plateNumber; // SPZ tahače

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type; // Typ vozidla (TAHAČ/SÓLO)

    @Column(length = 50)
    private String soloSubType; // Podtyp sólového vozidla (nosič kontejnerů, ruka atd.)

    @Column(length = 100)
    private String brand; // Značka tahače (např. Scania, Volvo)

    @Column(length = 100)
    private String model; // Model a motorizace tahače (např. R500, FH16)

    @Column(length = 250)
    private String notes; // Poznámky k tahači (např. servis, přidělení, platnost STK)
}
