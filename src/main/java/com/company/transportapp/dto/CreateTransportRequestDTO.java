package com.company.transportapp.dto;

import com.company.transportapp.model.enums.Enums;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateTransportRequestDTO {

    @NotNull
    private Long truckId;

    private Long trailerId;  // Povinné jen dle typu trucku - řeší service

    @NotNull
    private long driverId;

    @NotNull
    private Enums.TransportType type;

    @NotNull
    private Enums.Direction direction;

    private String clientName;
    private String clientAddress;
    private String clientIco;
    private Double clientPrice;

    private String dispatcherNotes;

    private List<CargoItemRequestDTO> cargoItems;
}
