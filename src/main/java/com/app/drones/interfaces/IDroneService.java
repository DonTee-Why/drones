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

    Drone loadDrone(String serialNumber, Set<Medication> medications) throws ResourceNotFoundException;

    Map<String, Object> getLoadedMedications(String serialNumber) throws ResourceNotFoundException;

    List<Drone> getAvailableDrones();

    int getBatteryLevel(String serialNumber) throws ResourceNotFoundException;

    int getTotalWeightOfItems(Set<Medication> medications);
}
