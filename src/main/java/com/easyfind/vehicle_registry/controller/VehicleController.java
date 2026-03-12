package com.easyfind.vehicle_registry.controller;

import com.easyfind.vehicle_registry.dto.VehicleResponseDTO;
import com.easyfind.vehicle_registry.model.Vehicle;
import com.easyfind.vehicle_registry.repository.VehicleRepository;
import com.easyfind.vehicle_registry.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {



    private final VehicleService vehicleService;

    public VehicleController(VehicleRepository vehicleRepository, VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping
    public List<VehicleResponseDTO> getAll() {
        return vehicleService.getAllVehiclesDTO();
    }

    @PostMapping
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.register(vehicle);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }
}