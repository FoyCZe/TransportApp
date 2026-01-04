package com.company.transportapp.model.entities;


import com.company.transportapp.model.enums.EmployeeRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="employees")
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    // Povinné údaje
    @NotBlank(message = "Jméno je povinné")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Příjmení je povinné")
    @Column( nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Telefonní číslo je povinné")
    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber;

    @NotNull(message = "Role zaměstnance musí být vyplněna")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EmployeeRole role;

    // Nepovinné údaje
    @Email(message = "Neplatný formát emailu")
    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 20)
    private String birthNumber;

    @Column(length = 20)
    private String idCardNumber;

    @Column(length = 20)
    private String drivingLicenseNumber;

    @Column(length = 20)
    private String adrLicenseNumber;

    @Column(length = 250)
    private String address;

    @Column(length = 20)
    private String passportNumber;

}
