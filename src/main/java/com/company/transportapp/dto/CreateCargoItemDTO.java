package com.company.transportapp.dto;

import com.company.transportapp.model.enums.CargoItemType;
import lombok.Getter;

@Getter
public class CreateCargoItemDTO {

    private CargoItemType itemType;

    // Container
    private String releaseCode;
    private String containerNumber;
    private String containerSize;
}
