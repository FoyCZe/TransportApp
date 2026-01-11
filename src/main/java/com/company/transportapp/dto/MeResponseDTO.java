package com.company.transportapp.dto;

import com.company.transportapp.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MeResponseDTO {

    private String username;
    private UserRole role;
    private boolean enabled;
}
