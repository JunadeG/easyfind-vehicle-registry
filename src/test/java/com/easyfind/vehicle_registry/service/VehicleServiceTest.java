package com.easyfind.vehicle_registry.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.easyfind.vehicle_registry.model.Vehicle;
import com.easyfind.vehicle_registry.repository.VehicleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @ParameterizedTest
    @ValueSource(ints = {2027, 2099, 3000}) // Testing multiple future years
    @DisplayName("Should throw 400 Bad Request for future vehicle years")
    void register_ShouldThrowException_ForFutureYears(int futureYear) {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(futureYear);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vehicleService.register(vehicle);
        });

        // Verify the status is 400
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());

        // Verify we never tried to save to the DB
        verify(vehicleRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should successfully save vehicle with current or past year")
    void register_ShouldSave_WhenYearIsValid() {
        // Arrange
        Vehicle validVehicle = new Vehicle();
        validVehicle.setYear(2024);
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(validVehicle);

        // Act
        Vehicle result = vehicleService.register(validVehicle);

        // Assert
        assertNotNull(result);
        verify(vehicleRepository, times(1)).save(validVehicle);
    }
}