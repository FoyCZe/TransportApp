package com.company.transportapp.service;

import com.company.transportapp.model.entities.Trailer;
import com.company.transportapp.model.enums.TrailerType;
import com.company.transportapp.repository.TrailerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrailerService {

    private final TrailerRepository repo;

    public List<Trailer> list() {
        return repo.findAll();
    }

    public Trailer get(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Trailer s ID " + id + " nenalezen."));
    }

    public Trailer create(Trailer t) {
        return repo.save(t);
    }

    public Trailer update(Long id, Trailer t) {
        Trailer db = get(id);

        db.setPlateNumber(t.getPlateNumber());
        db.setType(t.getType());
        db.setBrand(t.getBrand());
        db.setNotes(t.getNotes());

        return repo.save(db);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trailer s ID " + id + " nenalezen.");
        }
        repo.deleteById(id);
    }

    public Optional<Trailer> findByPlateNumber(String plateNumber) {
        return repo.findByPlateNumber(plateNumber);
    }

    public List<Trailer> findByBrand(String brand) {
        return repo.findByBrandContainingIgnoreCase(brand);
    }

    public List<Trailer> findByType(TrailerType type) {
        return repo.findByType(type);
    }

    public List<Trailer> findBySubType(String subType) {
        return repo.findBySubTypeContainingIgnoreCase(subType);
    }
}
