package com.company.transportapp.repository;

import com.company.transportapp.model.entities.CargoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoItemRepository extends JpaRepository<CargoItem, Long> {
}
