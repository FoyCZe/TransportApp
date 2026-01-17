package com.company.transportapp.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "containers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String containerNumber;
    private String releaseCode;
    private String size;  // 20DV, 40HC ...
    private String sealNumber;

    private Double cargoWeight;
    private Double tareWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id")
    private Transport transport;
}
