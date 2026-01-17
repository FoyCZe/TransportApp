package com.company.transportapp.service;

import com.company.transportapp.dto.EmployeeRequestDTO;
import com.company.transportapp.dto.EmployeeResponseDTO;
import com.company.transportapp.mapper.EmployeeMapper;
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
    public List<EmployeeResponseDTO> list() {
        return repo.findAll()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    /**
     * Vrátí zaměstnance podle ID
     * @throws ResponseStatusException 404 pokud zaměstnanec neexistuje
     */
    public EmployeeResponseDTO get(Long id) {
        return EmployeeMapper.toResponse(
                repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Zaměstnanec nenalezen"))
        );
    }

    /**
     * Vytvoří nového zaměstnance, s kontrolou duplicity e-mailu a telefonu
     */
    public EmployeeResponseDTO create(EmployeeRequestDTO dto) {
        Employee e = EmployeeMapper.toEntity(dto);
        return EmployeeMapper.toResponse(repo.save(e));
    }

    /**
     * Aktualizuje existujícího zaměstnance
     */
    public EmployeeResponseDTO update(Long id, EmployeeRequestDTO dto) {
        Employee e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Zaměstnanec nenalezen"));
        EmployeeMapper.updateEntity(e, dto);
        return EmployeeMapper.toResponse(repo.save(e));

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
    public List<EmployeeResponseDTO> findByFirstName(String firstName) {
        return repo.findByFirstNameContainingIgnoreCase(firstName)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    /**
     * Vyhledávání podle příjmení
     */
    public List<EmployeeResponseDTO> findByLastName(String lastName) {
        return repo.findByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    /**
     * Vyhledávání podle emailu
     */
    public EmployeeResponseDTO findByEmail(String email) {
        return repo.findByEmail(email)
                .map(EmployeeMapper::toResponse)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Zaměstnanec s emailem " + email + " nenalezen."));
    }

    /**
     * Vyhledávání podle telefonního čísla
     */
    public EmployeeResponseDTO findByPhoneNumber(String phoneNumber) {
        return repo.findByPhoneNumber(phoneNumber)
                .map(EmployeeMapper::toResponse)
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
