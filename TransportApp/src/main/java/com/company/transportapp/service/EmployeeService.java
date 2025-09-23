package com.company.transportapp.service;

import com.company.transportapp.model.Employee;
import com.company.transportapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repo;

    public List<Employee> list(){return repo.findAll();}
    public Employee get(Long id){return repo.findById(id).orElseThrow();}
    public Employee create(Employee e){return repo.save(e);}
    public Employee update(Long id, Employee e){
        Employee db = get(id);
        db.setFirstName(e.getFirstName());
        db.setLastName(e.getLastName());
        db.setPhone(e.getPhone());
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
    public void delete(Long id){repo.deleteById(id);}
}
