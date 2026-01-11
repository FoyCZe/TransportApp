package com.company.transportapp.model.entities;

import com.company.transportapp.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // SPZ vozidla = přihlašovací jméno
    @Column(nullable = false, unique = true, length = 15)
    private String username;

    // Předem vygenerované heslo (uložené jako hash)
    @Column(nullable = false)
    private String password;

    // Role: DRIVER, DISPATCHER, ADMIN
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    // Povolení účtu(true = aktivní, false = zablokovaný)
    @Column(nullable = false)
    private boolean enabled = true;

    // SPZ vozidla -> propojení uživatele s vozidlem
    @Column(length = 15)
    private String truckPlate;

    public User(String username, String password, UserRole role, String truckPlate) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = true;  // Při vytvoření je účet aktivní
        this.truckPlate = truckPlate;
    }
}
