package com.company.transportapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneratedPasswordDTO {

    private String username;  // SPZ vozidla (login)
    private String password;  // Vygenerované heslo (zobrazené jen jednou)
}
