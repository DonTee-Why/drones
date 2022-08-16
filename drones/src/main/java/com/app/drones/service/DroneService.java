package com.app.drones.service;

import com.app.drones.interfaces.IDroneService;
import com.app.drones.model.Drone;
import com.app.drones.model.Medication;
import com.app.drones.model.State;
import com.app.drones.repository.DroneRepository;
import com.app.drones.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneService implements IDroneService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public Drone getDrone(String serialNumber){
        return droneRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<Drone> getAllDrones(){
        return droneRepository.findAll();
    }

    @Override
    public Drone loadDrone(Drone drone, List<Medication> medications){
        if (drone.getBattery() < 25) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        drone.setState(State.LOADING);
        drone.setMedications(medications);
        drone.setState(State.LOADED);
        droneRepository.save(drone);
        return drone;
    }

    @Override
    public List<Medication> checkLoadedMedications(String serialNumber) {
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        return drone.getMedications();
    }

    @Override
    public List<Drone> getAvailableDrones(){
        return droneRepository.findAllByState(State.IDLE);
    }

    @Override
    public int checkBatteryLevel(String serialNumber){
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        return drone.getBattery();
    }
}
