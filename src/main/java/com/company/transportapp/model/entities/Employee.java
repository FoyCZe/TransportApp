package com.company.transportapp.model.entities;


import com.company.transportapp.model.enums.Enums;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    // Povinné údaje
    @Column(nullable = false)
    private String firstName;

    @Column( nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    // Nepovinné údaje
    @Column(unique = true)
    private String email;

    private String birthNumber;
    private String idCardNumber;
    private String drivingLicenseNumber;
    private String adrLicenseNumber;
    private String address;
    private String passportNumber;

    // Role uživatele v systému
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    public enum Role {
        DRIVER,
        DISPATCHER,
        ADMIN
    }
}
