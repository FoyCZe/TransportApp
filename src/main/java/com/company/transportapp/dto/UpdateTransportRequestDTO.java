package com.company.transportapp.dto;

import com.company.transportapp.model.enums.Enums;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateTransportRequestDTO {

    private Long truckId;
    private Long trailerId;
    private Long driverId;

    private Enums.TransportType type;
    private Enums.Direction direction;

    private String clientName;
    private String clientAddress;
    private String clientIco;
    private Double clientPrice;

    private String status;
    private String driverNotes;
    private String dispatcherNotes;

    private List<CargoItemRequestDTO> cargoItems;
}
