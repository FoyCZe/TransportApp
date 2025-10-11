package com.company.transportapp.controller;

import com.company.transportapp.model.entities.Trailer;
import com.company.transportapp.model.enums.TrailerType;
import com.company.transportapp.service.TrailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/trailers")
@RequiredArgsConstructor
public class TrailerController {

    private final TrailerService service;

    @GetMapping
    public List<Trailer> listAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Trailer get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public Trailer create(@RequestBody Trailer t) {
        return service.create(t);
    }

    @PutMapping("/{id}")
    public Trailer update(@PathVariable Long id, @RequestBody Trailer t) {
        return service.update(id, t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    // Vyhledávání podle SPZ
    @GetMapping("/search/plate")
    public Trailer searchByPlate(@RequestParam String plateNumber) {
        return service.findByPlateNumber(plateNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Trailer s SPZ " + plateNumber + " nenalezen."));
    }

    // Vyhledávání podle brandu
    @GetMapping("/search/brand")
    public List<Trailer> searchByBrand(@RequestParam String brand) {
        return service.findByBrand(brand);
    }

    // Vyhledávání podle typu (TRAILER / SEMITRAILER)
    @GetMapping("/search/type")
    public List<Trailer> searchByType(@RequestParam TrailerType type) {
        return service.findByType(type);
    }

    // Vyhledávání podle podtypu (Frigo, Nosič kontejneru)
    @GetMapping("/search/subtype")
    public List<Trailer> searchBySubType(@RequestParam String subType) {
        return service.findBySubType(subType);
    }
}
