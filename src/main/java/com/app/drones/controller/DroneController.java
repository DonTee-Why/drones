package com.app.drones.controller;

import com.app.drones.exception.ResourceNotFoundException;
import com.app.drones.model.Drone;
import com.app.drones.model.Medication;
import com.app.drones.service.DroneService;
import com.app.drones.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/drones")
public class DroneController {

    @Autowired
    private final DroneService droneService;

    @Autowired
    private final MedicationService medicationService;

    public DroneController(DroneService droneService, MedicationService medicationService) {
        this.droneService = droneService;
        this.medicationService = medicationService;
    }

    // GET http://locallhost:8080/drones

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Drone> getAllDrones(){
        return droneService.getAllDrones();
    }

    // POST http://locallhost:8080/drones
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Drone register(@Valid @RequestBody Drone drone){
        return droneService.registerDrone(drone);
    }

    // GET http://locallhost:8080/drones/123-23234-23232332
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{serialNumber}")
    public Drone getDrone(@PathVariable String serialNumber) throws ResourceNotFoundException {
        return droneService.getDrone(serialNumber);
    }

    // POST http://locallhost:8080/drones/load
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/load/{serialNumber}")
    public Drone loadDrone(@PathVariable String serialNumber, @Valid @RequestBody Set<Medication> medications) throws ResponseStatusException, ResourceNotFoundException {
        return droneService.loadDrone(serialNumber, medications);
    }

    // POST http://locallhost:8080/drones/loaded/123-23234-23232332
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/loaded/{serialNumber}")
    public Map<String, Object> getLoadedMedication(@Valid @PathVariable String serialNumber) throws ResourceNotFoundException{
        return droneService.getLoadedMedications(serialNumber);
    }

    // GET http://locallhost:8080/drones/available
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/available")
    public List<Drone> getAvailableDrones(){
        return droneService.getAvailableDrones();
    }

    // GET http://locallhost:8080/drones/battery
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/battery/{serialNumber}")
    public int getBatteryLevel(@PathVariable String serialNumber) throws ResourceNotFoundException {
        return droneService.getBatteryLevel(serialNumber);
    }

}
