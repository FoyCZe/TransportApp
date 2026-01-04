package com.company.transportapp.repository;

import com.company.transportapp.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Hledání uživatele podle SPZ (username)
    Optional<User> findByUsername(String username);

    // Hledání všech účtů podle vozidla
    Optional<User> findByTruckPlate(String truckPlate);
}
