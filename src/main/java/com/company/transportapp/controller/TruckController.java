package com.company.transportapp.controller;

import com.company.transportapp.model.entities.Truck;
import com.company.transportapp.model.enums.TruckType;
import com.company.transportapp.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
@RequiredArgsConstructor
public class TruckController {

    private final TruckService service;

    // Seznam všech vozidel
    @GetMapping
    public List<Truck> listAll() {
        return service.list();
    }

    // Vyhledávání vozidla podle SPZ
    @GetMapping("/search/byPlateNumber")
    public Truck searchByPlateNumber(String plateNumber) {
        return service.findByPlateNumber(plateNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Vozidlo s SPZ " + plateNumber + " nenalezeno."));
    }

    // Vyhledávání vozidel podle značky
    @GetMapping("/search/byBrand")
    public List<Truck> searchByBrand(@RequestParam String brand) {
        return service.findByBrand(brand);
    }

    // Vyhledávání vozidel podle modelu
    @GetMapping("/search/byModel")
    public List<Truck> searchByModel(@RequestParam String model) {
        return service.findByModel(model);
    }

    // Vyhledávání podle typu vozidla
    @GetMapping("/search/byType")
    public List<Truck> searchByType(@RequestParam TruckType type) {
        return service.findByType(type);
    }

    // Vyhledávání podle podtypu vozidla
    @GetMapping("/search/bySoloSubType")
    public List<Truck> searchBySoloSubType(@RequestParam String soloSubType) {
        return service.findBySoloSubType(soloSubType);
    }
}
