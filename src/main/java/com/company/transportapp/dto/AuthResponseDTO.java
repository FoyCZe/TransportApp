package com.company.transportapp.dto;

import com.company.transportapp.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDTO {


    // Krátkodobý JWT(Authorization: Bearer ...)
    private String accessToken;

    // Přihlašovací jméno (SPZ vozidla)
    private String username;

    // Role uživatele (DRIVER / DISPATCHER / OWNER)
    private UserRole role;
}
