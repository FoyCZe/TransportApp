package com.company.transportapp.repository;


import com.company.transportapp.model.entities.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {
    List<Stop> findByTransport_Id(Long transportId);
}
