package com.company.transportapp.dto;

import com.company.transportapp.model.enums.CargoItemType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoItemRequestDTO {

    @NotNull
    private CargoItemType itemType;

    // Pouze pro kontejner
    private Long containerId;

    // Pouze pro PALETTE / PIECE / TANK
    private Integer quantity;

    // Hmotnost nákladu (bez tary)
    private Double cargoWeight;
}
