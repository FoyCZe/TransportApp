package com.company.transportapp.repository;

import com.company.transportapp.model.entities.Trailer;
import com.company.transportapp.model.enums.TrailerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrailerRepository extends JpaRepository<Trailer, Long> {

    Optional<Trailer> findByPlateNumber(String plateNumber);

    List<Trailer> findByBrandContainingIgnoreCase(String brand);

    List<Trailer> findByType(TrailerType type);

    List<Trailer> findBySubTypeContainingIgnoreCase(String subType);


}
