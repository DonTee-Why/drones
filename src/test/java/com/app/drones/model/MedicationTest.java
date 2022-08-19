package com.app.drones.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicationTest {

    @Test
    @DisplayName(value = "Create New Medication")
    void createNewDrone(){
        Medication medication = new Medication();
        medication.setId(1L);
        medication.setName("Medication1");
        medication.setWeight(48);
        medication.setCode("Code_1234");
        medication.setImage("Test_Image.jpeg");

        assertNotNull(medication);
        assertEquals("Test_Image.jpeg", medication.getImage());
        assertInstanceOf(Medication.class, medication);
    }
}