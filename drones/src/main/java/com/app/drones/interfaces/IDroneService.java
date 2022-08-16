package com.app.drones.interfaces;

import com.app.drones.model.Drone;
import com.app.drones.model.Medication;
import com.app.drones.model.State;
import com.app.drones.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface IDroneService {

    Drone registerDrone(Drone drone);

    Drone getDrone(String serialNumber);

    List<Drone> getAllDrones();

    Drone loadDrone(Drone drone, List<Medication> medications);

    List<Medication> checkLoadedMedications(String serialNumber);

    List<Drone> getAvailableDrones();

    int checkBatteryLevel(String serialNumber);
}
