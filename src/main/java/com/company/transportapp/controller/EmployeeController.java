package com.company.transportapp.controller;

import com.company.transportapp.dto.EmployeeRequestDTO;
import com.company.transportapp.dto.EmployeeResponseDTO;
import com.company.transportapp.model.entities.Employee;
import com.company.transportapp.service.EmployeeService;
import jakarta.validation.Valid;
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
    public List<EmployeeResponseDTO> getAllEmployees() {
        return service.list();
    }

    // Vrátí zaměstnance podle ID
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        return service.get(id);
    }

    // Vytvoří nového zaměstnance
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    // Aktualizuje zaměstnance podle ID
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // Smaže zaměstnance podle ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        service.delete(id);
    }

    // Vyhledávání podle jména
    @GetMapping("/search/firstname")
    public List<EmployeeResponseDTO> searchByFirstName(@RequestParam String value) {
        return service.findByFirstName(value);
    }

    // Vyhledávání podle příjmení
    @GetMapping("/search/lastname")
    public List<EmployeeResponseDTO> searchByLastName(@RequestParam String value) {
        return service.findByLastName(value);
    }

    // Vyhledávání podle emailu
    @GetMapping("/search/email")
    public EmployeeResponseDTO findByEmail(@RequestParam String value) {
        return service.findByEmail(value);
    }

    // Vyhledávání podle telefonního čísla
    @GetMapping("/search/phone")
    public EmployeeResponseDTO findByPhoneNumber(@RequestParam String value) {
        return service.findByPhoneNumber(value);
    }
}
