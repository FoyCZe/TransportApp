package com.company.transportapp.service;

import com.company.transportapp.dto.*;
import com.company.transportapp.mapper.TransportMapper;
import com.company.transportapp.model.entities.CargoItem;
import com.company.transportapp.model.entities.Employee;
import com.company.transportapp.model.entities.Transport;
import com.company.transportapp.model.entities.Truck;
import com.company.transportapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportService {

    private final TransportRepository transportRepo;
    private final TruckRepository truckRepo;
    private final TrailerRepository trailerRepo;
    private final EmployeeRepository employeeRepo;
    private final StopRepository stopRepo;
    private final DocumentRepository documentRepo;
    private final CargoItemRepository cargoItemRepo;


    // Vrátí všechny přepravy
    public List<TransportResponseDTO> list(){
        return transportRepo.findAll()
                .stream()
                .map(TransportMapper::toResponse)
                .toList();
    }

    // Přeprava podle ID
    public TransportResponseDTO get(Long id){
        Transport t = transportRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Přeprava nenalezena"));
        return TransportMapper.toResponse(t);
    }

    // Vytvoření přepravy
    public TransportResponseDTO create(TransportRequestDTO dto) {

        // Najít truck
        Truck truck = truckRepo.findById(dto.getTruckId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vozidlo nenalezeno"));

        Transport t = new Transport();
        t.setTruck(truck);
        t.setType(dto.getType());
        t.setDirection(dto.getDirection());
        t.setStatus("VYTVOŘENO");
        t.setCreatedAt(LocalDateTime.now());
        t.setDispatcherNotes(dto.getDispatcherNotes());

        for (CreateCargoItemDTO ci : dto.getCargoItems()) {
            CargoItem item = new CargoItem();
            item.setTransport(t);
            item.setItemType(ci.getItemType());
            item.setReleaseCode(ci.getReleaseCode());
            item.setContainerNumber(ci.getContainerNumber());
            item.setContainerSize(ci.getContainerSize());
            t.getCargoItems().add(item);
        }

        return TransportMapper.toResponse(transportRepo.save(t));
    }

    public TransportResponseDTO complete(Long transportId, CompleteTransportRequestDTO dto) {

        Transport t = transportRepo.findById(transportId).orElseThrow();

        t.setDriver(employeeRepo.findById(dto.getDriverId()).orElseThrow());
        t.setTrailer(
                dto.getTrailerId() != null
                ? trailerRepo.findById(dto.getTrailerId()).orElseThrow()
                        : null
        );

        double totalWeight = 0;

        for (CompleteCargoItemDTO ci : dto.getCargoItems()) {
            CargoItem item = cargoItemRepo.findById(ci.getCargoItemId()).orElseThrow();

            item.setTareWeight(ci.getTareWeight());
            item.setCargoWeight(ci.getCargoWeight());
            item.setSealNumber(ci.getSealNumber());

            if (ci.getCargoWeight() != null) {
                totalWeight += ci.getCargoWeight();
            }
        }

        t.setTotalWeight(totalWeight);
        t.setDriverNotes(dto.getDriverNotes());
        t.setStatus("PROBÍHÁ");

        return TransportMapper.toResponse(transportRepo.save(t));
    }
}
