package com.app.drones.service;

import com.app.drones.exception.ResourceNotFoundException;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public Drone getDrone(String serialNumber) throws ResourceNotFoundException {
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone == null) {
            throw new ResourceNotFoundException("Drone not found.");
        }
        return drone;
    }

    @Override
    public List<Drone> getAllDrones(){
        return droneRepository.findAll();
    }

    @Override
    public Drone loadDrone(String serialNumber, Set<Medication> medications) throws ResourceNotFoundException {
        Drone drone = this.getDrone(serialNumber);
        int sumOfMedicationWeights = this.getTotalWeightOfItems(medications);

        if (drone == null) {
            throw new ResourceNotFoundException("Drone not found.");
        } else if (drone.getBattery() < 25) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Drone battery is low.");
        } else if (drone.getWeightLimit() < sumOfMedicationWeights) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Medication weight exceeded limit");
        } else if (drone.getState() != State.IDLE) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This drone is not available");
        }

        // Simulate the delivery process. For every delivery, the battery reduces by 5%
        drone.setState(State.LOADING);
        drone.setMedications(medications);
        drone.setState(State.LOADED);
        drone.setState(State.DELIVERING);
        drone.setBattery(drone.getBattery() - 5);
        drone.setState(State.DELIVERED);
        return droneRepository.save(drone);
    }

    @Override
    public Map<String, Object> getLoadedMedications(String serialNumber) throws ResourceNotFoundException {
        Map<String, Object> res = new HashMap<>();
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone == null) {
            throw new ResourceNotFoundException("Drone not found.");
        }

        res.put("quantity", drone.getMedications().size());
        res.put("medications", drone.getMedications());

        return res;
    }

    @Override
    public List<Drone> getAvailableDrones(){
        return droneRepository.findAllByState(State.IDLE);
    }

    @Override
    public int getBatteryLevel(String serialNumber) throws ResourceNotFoundException{
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone == null) {
            throw new ResourceNotFoundException("Drone not found.");
        }
        return drone.getBattery();
    }

    @Override
    public int getTotalWeightOfItems(Set<Medication> medications) {
        int sum = 0;
        for (Medication medication : medications) {
            sum += medication.getWeight();
        }
        return sum;
    }
}
