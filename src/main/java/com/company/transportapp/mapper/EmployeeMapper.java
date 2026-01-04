package com.company.transportapp.mapper;

import com.company.transportapp.dto.EmployeeRequestDTO;
import com.company.transportapp.dto.EmployeeResponseDTO;
import com.company.transportapp.model.entities.Employee;

public class EmployeeMapper {

    public static EmployeeResponseDTO toResponse(Employee e) {
        return new EmployeeResponseDTO(
                e.getId(),
                e.getFirstName(),
                e.getLastName(),
                e.getPhoneNumber(),
                e.getEmail(),
                e.getRole()
        );
    }

    public static Employee toEntity(EmployeeRequestDTO dto) {
        Employee e = new Employee();
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setPhoneNumber(dto.getPhoneNumber());
        e.setEmail(dto.getEmail());
        e.setRole(dto.getRole());
        e.setBirthNumber(dto.getBirthNumber());
        e.setIdCardNumber(dto.getIdCardNumber());
        e.setDrivingLicenseNumber(dto.getDrivingLicenseNumber());
        e.setAdrLicenseNumber(dto.getAdrLicenseNumber());
        e.setPassportNumber(dto.getPassportNumber());
        e.setAddress(dto.getAddress());
        return e;
    }

    public static void updateEntity(Employee e, EmployeeRequestDTO dto) {
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setPhoneNumber(dto.getPhoneNumber());
        e.setEmail(dto.getEmail());
        e.setRole(dto.getRole());
        e.setBirthNumber(dto.getBirthNumber());
        e.setIdCardNumber(dto.getIdCardNumber());
        e.setDrivingLicenseNumber(dto.getDrivingLicenseNumber());
        e.setAdrLicenseNumber(dto.getAdrLicenseNumber());
        e.setPassportNumber(dto.getPassportNumber());
        e.setAddress(dto.getAddress());
    }
}
