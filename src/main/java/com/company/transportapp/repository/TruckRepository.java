package com.company.transportapp.repository;

import com.company.transportapp.model.entities.Truck;
import com.company.transportapp.model.enums.TruckType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TruckRepository extends JpaRepository<Truck, Long> {

    Optional<Truck> findByPlateNumber(String plateNumber);

    List<Truck> findByBrandContainingIgnoreCase(String brand);

    List<Truck> findByModelContainingIgnoreCase(String model);

    List<Truck> findByType(TruckType type);

    List<Truck> findBySoloSubTypeContainingIgnoreCase(String soloSubType);
}
