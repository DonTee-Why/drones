package com.app.drones.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {

    @Test
    @DisplayName(value = "Create New Drone")
    void createNewDrone(){
        Drone drone = new Drone();
        Medication medication = new Medication();
//        medication.setId(UUID.randomUUID().);
        medication.setName("Medication1");
        medication.setWeight(75);
        medication.setCode("Code_1234");
        medication.setImage("Test_Image.jpeg");

        Set<Medication> medications = new HashSet<>();
        
        medications.add(medication);
        drone.setSerialNumber(UUID.randomUUID().toString());
        drone.setModel(Model.MIDDLEWEIGHT);
        drone.setBattery(75);
        drone.setWeightLimit(400);
        drone.setMedications(medications);

        assertNotNull(drone);
        assertEquals("MIDDLEWEIGHT", drone.getModel().name());
        assertEquals("IDLE", drone.getState().name());
        assertInstanceOf(Drone.class, drone);
    }
}