package com.app.drones.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicationTest {

    @Test
    void create_new_drone(){
        Medication medication = new Medication();
        medication.setName("Medication1");
        medication.setWeight(48);
        medication.setCode("Code_1234");
        medication.setImage("Test_Image.jpeg");

        assertNotNull(medication);
        assertEquals("Test_Image.jpeg", medication.getImage());
//        assertTrue(true);
    }
}