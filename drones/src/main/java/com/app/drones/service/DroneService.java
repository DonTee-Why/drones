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
        return droneRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<Drone> getAllDrones(){
        return droneRepository.findAll();
    }

    @Override
    public Drone loadDrone(Drone drone, Set<Medication> medications){
        int sumOfMedicationWeights = this.getTotalWeightOfItems(medications);

        if (drone.getBattery() < 25) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Drone battery is low.");
        } else if (drone.getWeight() < sumOfMedicationWeights) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Medication weight exceed limit");
        }

        drone.setState(State.LOADING);
        drone.setMedications(medications);
        drone.setState(State.LOADED);
        droneRepository.save(drone);
        return drone;
    }

    @Override
    public Map<String, Object> getLoadedMedications(String serialNumber) {
        Map<String, Object> res = new HashMap<>();
        Drone drone = droneRepository.findBySerialNumber(serialNumber);

        res.put("quantity", drone.getMedications().size());
        res.put("medications", drone.getMedications());

        return res;
    }

    @Override
    public List<Drone> getAvailableDrones(){
        return droneRepository.findAllByState(State.IDLE);
    }

    @Override
    public int getBatteryLevel(String serialNumber){
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        return drone.getBattery();
    }

    @Override
    public int getTotalWeightOfItems(Set<Medication> medications) {
        int sum = 0;
        for (Medication medication :
                medications) {
            sum += medication.getWeight();
        }
        return sum;
    }
}
