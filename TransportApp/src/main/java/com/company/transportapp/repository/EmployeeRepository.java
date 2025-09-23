package com.company.transportapp.repository;


import com.company.transportapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByPhoneNumber(String phoneNumber);
    Optional<Employee> findByEmail(String email);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByFirstName(String firstName);
}
