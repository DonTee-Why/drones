package com.app.drones.repository;

import com.app.drones.model.Drone;
import com.app.drones.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, String> {
    Drone findBySerialNumber(String serialNumber);

    List<Drone> findAllByState(State state);
}
