package com.company.transportapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContainerResponseDTO {

    private Long id;
    private String containerNumber;
    private String releaseCode;
    private String containerSize;
    private String sealNumber;

    private Double cargoWeight;
    private Double tareWeight;
}
