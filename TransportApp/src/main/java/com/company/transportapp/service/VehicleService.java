package com.company.transportapp.service;


import com.company.transportapp.model.Vehicle;
import com.company.transportapp.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository repo;

    public List<Vehicle> list(){ return repo.findAll();}
    public Vehicle get(Long id){return repo.findById(id).orElseThrow();}
    public Vehicle create(Vehicle v){return repo.save(v);}
    public Vehicle update(Long id, Vehicle v){
        Vehicle db = get(id);
        db.setTruckPlate(v.getTruckPlate());
        db.setCompanyName(v.getCompanyName());
        db.setType(v.getType());
        return repo.save(db);
    }
    public void delete(Long id){repo.deleteById(id);}
}
