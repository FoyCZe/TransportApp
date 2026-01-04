package com.company.transportapp.dto;


import com.company.transportapp.model.enums.EmployeeRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    private String phoneNumber;

    @Email
    private String email;

    @NotNull
    private EmployeeRole role;

    // Interní údaje - posílá jen admin/dispečer
    private String birthNumber;
    private String idCardNumber;
    private String drivingLicenseNumber;
    private String adrLicenseNumber;
    private String passportNumber;
    private String address;
}
