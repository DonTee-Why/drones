package com.app.drones.model;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {

    @Test
    void create_new_drone(){
        Drone drone = new Drone();
        Medication medication = new Medication(
                "Medication1",
                75,
                "Code_1234",
                "Test_Image.jpeg"
        );
        List<Medication> medications = new ArrayList<>();
        
        medications.add(medication);
        drone.setSerialNumber(UUID.randomUUID().toString());
        drone.setModel(Model.MIDDLEWEIGHT);
        drone.setBattery(75);
        drone.setWeight(400);
        drone.setState(State.IDLE);
        drone.setMedications(medications);

        System.out.println(drone.getSerialNumber());
        assertNotNull(drone);
        assertEquals("MIDDLEWEIGHT", drone.getModel().name());
    }
}