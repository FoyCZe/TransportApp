package com.company.transportapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContainerRequestDTO {

    private String containerNumber;  // Číslo kontejneru
    private String releaseCode;  // Uvolňovačka na kontejner
    private String containerSize;  // Velikost kontejneru (20DV, 40HC ...)
    private String sealNumber;  // Číslo plomby

    private Double cargoWeight;  // Hmotnost zboží
    private Double tareWeight;  // Hmotnost prázdného kontejneru (tara)
}
