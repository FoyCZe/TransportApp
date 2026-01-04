package com.company.transportapp.dto;


import com.company.transportapp.model.enums.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private EmployeeRole role;
}
