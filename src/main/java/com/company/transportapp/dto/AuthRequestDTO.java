package com.company.transportapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthRequestDTO {

    @NotBlank
    private String username; // SPZ

    @NotBlank
    private String password;
}
