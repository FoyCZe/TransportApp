package com.company.transportapp.dto;

import com.company.transportapp.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String username;  // SPZ
    private UserRole role;
}
