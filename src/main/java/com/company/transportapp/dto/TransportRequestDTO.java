package com.company.transportapp.dto;

import com.company.transportapp.model.entities.CargoItem;
import com.company.transportapp.model.enums.Enums;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TransportRequestDTO {

    @NotNull
    private Long truckId;  // ID tahače / sóla

    private Long trailerId;  // volitelné (povinné jen pro nesólo)

    @NotNull
    private Long driverId;

    @NotNull
    private Enums.TransportType type;  // CONTAINER / REEFER / TANK

    @NotNull
    private Enums.Direction direction;  // IMPORT / EXPORT / DOMESTIC

    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    private String driverNotes;
    private String dispatcherNotes;

    private List<CreateCargoItemDTO> cargoItems;

}
