package com.easyfind.vehicle_registry.service;

import com.easyfind.vehicle_registry.model.Vehicle;
import com.easyfind.vehicle_registry.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;



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
}
