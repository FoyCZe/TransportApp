package com.company.transportapp.service;

import com.company.transportapp.model.entities.Employee;
import com.company.transportapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;

    /**
     * Vrátí seznam všech zaměstnanců
     */
    public List<Employee> list() {
        return repo.findAll();
    }

    /**
     * Vrátí zaměstnance podle ID
     * @throws ResponseStatusException 404 pokud zaměstnanec neexistuje
     */
    public Employee get(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Zaměstnanec s ID " + id + " nenalezen."));
    }

    /**
     * Vytvoří nového zaměstnance, s kontrolou duplicity e-mailu a telefonu
     */
    public Employee create(Employee e) {
        validateUniqueEmailAndPhone(e);
        return repo.save(e);
    }

    /**
     * Aktualizuje existujícího zaměstnance
     */
    public Employee update(Long id, Employee e) {
        Employee db = get(id);

        // Kontrola duplicity emailu a telefonu (pokud se mění)
        if (!db.getEmail().equals(e.getEmail()) || !db.getPhoneNumber().equals(e.getPhoneNumber())) {
            validateUniqueEmailAndPhone(e);
        }

        db.setFirstName(e.getFirstName());
        db.setLastName(e.getLastName());
        db.setPhoneNumber(e.getPhoneNumber());
        db.setBirthNumber(e.getBirthNumber());
        db.setIdCardNumber(e.getIdCardNumber());
        db.setDrivingLicenseNumber(e.getDrivingLicenseNumber());
        db.setAdrLicenseNumber(e.getAdrLicenseNumber());
        db.setPassportNumber(e.getPassportNumber());
        db.setAddress(e.getAddress());
        db.setEmail(e.getEmail());
        db.setRole(e.getRole());

        return repo.save(db);
    }

    /**
     * Smaže zaměstnance podle ID
     */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Zaměstnanec s ID " + id + " nenalezen.");
        }
        repo.deleteById(id);
    }

    /**
     * Vyhledávání podle jména
     */
    public List<Employee> findByFirstName(String firstName) {
        return repo.findByFirstNameContainingIgnoreCase(firstName);
    }

    /**
     * Vyhledávání podle příjmení
     */
    public List<Employee> findByLastName(String lastName) {
        return repo.findByLastNameContainingIgnoreCase(lastName);
    }

    /**
     * Vyhledávání podle emailu
     */
    public Employee findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Zaměstnanec s emailem " + email + " nenalezen."));
    }

    /**
     * Vyhledávání podle telefonního čísla
     */
    public Employee findByPhoneNumber(String phoneNumber) {
        return repo.findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Zaměstnanec s telefonním číslem " + phoneNumber + " nenalezen."));
    }

    /**
     * Pomocná metoda pro kontrolu unikátnosti emailu a telefonu
     */
    private void validateUniqueEmailAndPhone(Employee e) {
        repo.findByEmail(e.getEmail()).ifPresent(emp -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email již existuje: " + e.getEmail());
        });

        repo.findByPhoneNumber(e.getPhoneNumber()).ifPresent(emp -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Telefonní číslo již existuje: " + e.getPhoneNumber());
        });
    }
}
