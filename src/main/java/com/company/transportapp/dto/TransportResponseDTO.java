package com.company.transportapp.dto;

import com.company.transportapp.model.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class TransportResponseDTO {

    private Long id;

    private Long truckId;
    private String truckPlate;

    private Long trailerId;
    private String trailerPlate;

    private Long driverId;
    private String driverName;

    private Enums.TransportType type;
    private Enums.Direction direction;

    private Double totalWeight;

    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    private String driverNotes;
    private String dispatcherNotes;

    private List<CargoItemResponseDTO> cargoItems;
}
