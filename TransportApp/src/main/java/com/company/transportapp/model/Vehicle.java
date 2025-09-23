package com.company.transportapp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String truckPlate;  // TRUCKPLATE / SPZ

    @Column(nullable = false)
    private String companyName;  // NAME OF VEHICLE OWNER


    private String type;  // CONTAINER / REEFER

    private Double maxLoad;  // WEIGHT
}
