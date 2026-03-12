package com.easyfind.vehicle_registry.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String licensePlate;
    private Integer year;
    private String status; // e.g., AVAILABLE, IN_USE, MAINTENANCE
    // ytrewq
}
