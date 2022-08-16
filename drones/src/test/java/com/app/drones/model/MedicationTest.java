package com.app.drones.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicationTest {

    @Test
    void create_new_drone(){
        Medication medication = new Medication(
                "Medication1",
                75,
                "Code_1234",
                "Test_Image.jpeg"
        );
        medication.setName("Medication1");
        medication.setWeight(48);
        medication.setCode("Code-1234");
        medication.setImage("Test_Image.jpeg");

        assertNotNull(medication);
        assertEquals("Test_Image.jpeg", medication.getImage());
//        assertTrue(true);
    }
}