package com.company.transportapp.repository;


import com.company.transportapp.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Vyhledávání podle emailu nebo telefonu
    Optional<Employee> findByPhoneNumber(String phoneNumber);
    Optional<Employee> findByEmail(String email);

    // Vyhledávání podle jména nebo příjmení (case insensitive)
    List<Employee> findByLastNameContainingIgnoreCase(String lastName);
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
}
