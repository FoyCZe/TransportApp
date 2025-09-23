package com.company.transportapp.repository;


import com.company.transportapp.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    List<Transport> findByVehicle_TruckPlate(String truckPlate);
    List<Transport> findByDriver_LastName(String lastName);
    List<Transport> findByContainerNumber(String containerNumber);
    List<Transport> findByReleaseCode(String releaseCode);
    List<Transport> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}
