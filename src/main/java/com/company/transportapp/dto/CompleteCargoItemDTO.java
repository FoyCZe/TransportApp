package com.company.transportapp.dto;

import lombok.Getter;

@Getter
public class CompleteCargoItemDTO {

    private Long cargoItemId;

    private Double tareWeight;
    private Double cargoWeight;
    private String sealNumber;
}
