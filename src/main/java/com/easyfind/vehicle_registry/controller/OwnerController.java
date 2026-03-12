package com.easyfind.vehicle_registry.controller;


import com.easyfind.vehicle_registry.model.Owner;
import com.easyfind.vehicle_registry.model.Vehicle;
import com.easyfind.vehicle_registry.repository.OwnerRepository;
import com.easyfind.vehicle_registry.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerRepository ownerRepository;
    private final VehicleRepository vehicleRepository;

    public OwnerController(OwnerRepository ownerRepository, VehicleRepository vehicleRepository) {
        this.ownerRepository = ownerRepository;
        this.vehicleRepository = vehicleRepository;
    }


    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerRepository.save(owner);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    @PostMapping("/assign-car")
    public ResponseEntity<String> assignCarToOwner(@RequestParam Long ownerId, @RequestParam Long vehicleId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setOwner(owner);
        owner.getVehicles().add(vehicle);
        ownerRepository.save(owner);

        return ResponseEntity.ok("Vehicle " + vehicle.getMake() + " assigned to " + owner.getName());
    }
}
