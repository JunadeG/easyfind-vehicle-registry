package com.easyfind.vehicle.controller;

import com.easyfind.vehicle.model.Vehicle;
import com.easyfind.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @PostMapping
    public Vehicle register(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}