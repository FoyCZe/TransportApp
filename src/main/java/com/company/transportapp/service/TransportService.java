package com.company.transportapp.service;

import com.company.transportapp.dto.TransportRequestDTO;
import com.company.transportapp.dto.TransportResponseDTO;
import com.company.transportapp.model.entities.Employee;
import com.company.transportapp.model.entities.Transport;
import com.company.transportapp.model.entities.Truck;
import com.company.transportapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


    public List<Transport> list(){return transportRepo.findAll();}
    public Transport get(Long id){return transportRepo.findById(id).orElseThrow();}

    public TransportResponseDTO create(TransportRequestDTO dto){
        Truck v = truckRepo.findById(truckId).orElseThrow();
        Employee d = employeeRepo.findById(driverId).orElseThrow();
        t.setTruck(v);
        t.setDriver(d);
        t.setStatus("Vytvořeno");
        t.setCreatedAt(LocalDateTime.now());
        return transportRepo.save(t);
    }

    public Transport update(Long id, Transport t){
        Transport db = get(id);
        db.setType(t.getType());
        db.setDirection(t.getDirection());
        db.setContainerNumber(t.getContainerNumber());
        db.setReleaseCode(t.getReleaseCode());
        return transportRepo.save(db);
    }
}
