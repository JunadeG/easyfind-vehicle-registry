package com.easyfind.vehicle_registry.repository;

import com.easyfind.vehicle_registry.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
