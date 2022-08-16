package com.app.drones.service;

import com.app.drones.interfaces.IMedicationService;
import com.app.drones.model.Drone;
import com.app.drones.model.Medication;
import com.app.drones.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService implements IMedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Optional<Medication> getMedication(String id) {
        return medicationRepository.findById(id);
    }

    @Override
    public Medication createMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public int getTotalWeight(List<Medication> medications) {
        int sum = 0;
        for (Medication medication :
                medications) {
            sum += medication.getWeight();
        }
        return sum;
    }

}
