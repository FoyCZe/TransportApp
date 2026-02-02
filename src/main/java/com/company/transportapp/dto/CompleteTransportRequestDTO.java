package com.company.transportapp.dto;

import lombok.Getter;

@Getter
public class CompleteTransportRequestDTO {

    private Long trailerId;
    private Long driverId;

    private List<CompleteCargoItemDTO> cargoItems;

    private String driverNotes;
}
