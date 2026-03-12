package com.easyfind.vehicle_registry.service;

import com.easyfind.vehicle_registry.dto.VehicleResponseDTO;
import com.easyfind.vehicle_registry.model.Vehicle;
import com.easyfind.vehicle_registry.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {


    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }



    public Vehicle register( Vehicle vehicle) {
      if (vehicle == null) {
          throw new ResponseStatusException(
                  HttpStatus.BAD_REQUEST,  "Vehicle data cannot be null"
          );
      }


      int currentYear = Year.now().getValue();
      if (vehicle.getYear() > currentYear) {
          throw new ResponseStatusException(
                  HttpStatus.BAD_REQUEST,
                  "Vehicle year cannot be in the future. Current year is " + currentYear
          );
      }
        return vehicleRepository.save(vehicle);
    }


    private VehicleResponseDTO convertDTO(Vehicle vehicle){
        VehicleResponseDTO dto = new VehicleResponseDTO();
        dto.setFullName(vehicle.getMake() + " " + vehicle.getModel());
        dto.setYear(vehicle.getYear());



        if (vehicle.getOwner() != null){
            dto.setOwnerName(vehicle.getOwner().getName());
        }else{
            dto.setOwnerName("Unassigned");
        }
        return dto;
    }

    public List<VehicleResponseDTO> getAllVehiclesDTO(){
        return vehicleRepository.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
    }
}
