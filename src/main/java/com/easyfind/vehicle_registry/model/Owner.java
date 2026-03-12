package com.easyfind.vehicle_registry.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
@Data
public class Owner {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String name;
     private String email;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "owner_vehicles",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private List<Vehicle> vehicles = new ArrayList<>();

}
