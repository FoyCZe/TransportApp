package com.company.transportapp.controller;

import com.company.transportapp.model.entities.Employee;
import com.company.transportapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // Umožní komunikaci s React frontendem
public class EmployeeController {

    private final EmployeeService service;

    // Vrátí seznam vše zaměstnanců
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {return ResponseEntity.ok(service.list());
    }

    // Vrátí zaměstnance podle ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    // Vytvoří nového zaměstnance
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee created = service.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Aktualizuje zaměstnance podle ID
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee
    ) {
        return ResponseEntity.ok(service.update(id, employee));
    }

    // Smaže zaměstnance podle ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Vyhledávání podle jména
    @GetMapping("/search/firstname")
    public ResponseEntity<List<Employee>> searchByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(service.findByFirstName(firstName));
    }

    // Vyhledávání podle příjmení
    @GetMapping("/search/lastname")
    public ResponseEntity<List<Employee>> searchByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(service.findByLastName(lastName));
    }

    // Vyhledávání podle emailu
    @GetMapping("/search/email")
    public ResponseEntity<Employee> searchByEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    // Vyhledávání podle telefonního čísla
    @GetMapping("/search/phone")
    public ResponseEntity<Employee> searchByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(service.findByPhoneNumber(phoneNumber));
    }
}
