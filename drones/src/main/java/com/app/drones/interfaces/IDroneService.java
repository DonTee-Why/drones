package com.app.drones.interfaces;

import com.app.drones.exception.ResourceNotFoundException;
import com.app.drones.model.Drone;
import com.app.drones.model.Medication;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface IDroneService {

    Drone registerDrone(Drone drone);

    Drone getDrone(String serialNumber) throws ResourceNotFoundException;

    List<Drone> getAllDrones();

    Drone loadDrone(Drone drone, Set<Medication> medications);

    Map<String, Object> getLoadedMedications(String serialNumber);

    List<Drone> getAvailableDrones();

    int getBatteryLevel(String serialNumber);

    int getTotalWeightOfItems(Set<Medication> medications);
}
