package com.app.drones.interfaces;

import com.app.drones.model.Drone;
import com.app.drones.model.Medication;

import java.util.List;
import java.util.Optional;


public interface IMedicationService {

    Optional<Medication> getMedication(Long id);

    Medication createMedication(Medication medication);
    List<Medication> getAllMedications();

    int getTotalWeight(List<Medication> medications);

}
