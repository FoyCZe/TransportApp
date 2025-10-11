package com.company.transportapp.service;

import com.company.transportapp.model.entities.Truck;
import com.company.transportapp.model.enums.VehicleType;
import com.company.transportapp.repository.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository repo;

    public List<Truck> list() {
        return repo.findAll();
    }

    public Truck get(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Vozidlo s ID " + id + " nenalezeno."));
    }

    public Truck create(Truck t) {
        return repo.save(t);
    }

    public Truck update(Long id, Truck t) {
        Truck db = get(id);

        db.setPlateNumber(t.getPlateNumber());
        db.setType(t.getType());
        db.setSoloSubType(t.getSoloSubType());
        db.setBrand(t.getBrand());
        db.setModel(t.getModel());
        db.setNotes(t.getNotes());

        return repo.save(db);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vozidlo s ID " + id + " nenalezeno.");
        }
        repo.deleteById(id);
    }

    public Optional<Truck> findByPlateNumber(String plateNumber) {
        return repo.findByPlateNumber(plateNumber);
    }

    public List<Truck> findByBrand(String brand) {
        return repo.findByBrandContainingIgnoreCase(brand);
    }

    public List<Truck> findByModel(String model) {
        return repo.findByModelContainingIgnoreCase(model);
    }

    public List<Truck> findByType(VehicleType type) {
        return repo.findByType(type);
    }

    public List<Truck> findBySoloSubType(String soloSubType) {
        return repo.findBySoloSubTypeContainingIgnoreCase(soloSubType);
    }
}
