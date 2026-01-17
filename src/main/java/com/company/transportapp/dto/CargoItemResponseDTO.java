package com.company.transportapp.dto;

import com.company.transportapp.model.enums.CargoItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CargoItemResponseDTO {

    private Long id;
    private CargoItemType itemType;

    // Kontejner (pokud existuje)
    private Long containerId;
    private String containerNumber;
    private String containerSize;
    private String sealNumber;
    private Double tareWeight;

    private Integer quantity;
    private Double cargoWeight;
}
