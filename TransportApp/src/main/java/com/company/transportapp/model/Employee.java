package com.company.transportapp.model;


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

    @Column(nullable=false) private String firstName;
    @Column(nullable=false) private String lastName;
    @Column(nullable=false) private String phone;

    private String birthNumber;
    private String email;
    private String idCardNumber;
    private String drivingLicenseNumber;
    private String adrLicenseNumber;
    private String address;
    private String passportNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Enums.Role role;  //DRIVER/DISPATCHER/ADMIN
}
