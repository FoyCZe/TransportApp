package com.company.transportapp.model.entities;

import com.company.transportapp.model.enums.CargoItemType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cargo_items")
@Getter
@Setter
public class CargoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vazba na přepravu
    @ManyToOne(optional = false)
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CargoItemType itemType;

    /**
     * Počet kusů (palety, kusy)
     * U kontejneru vždy = 1
     */
    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    private Container container;

    private Double cargoWeight;
}
